package neuralnet

import org.jblas.DoubleMatrix
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

class NNModel {
    private var inputUnitCount = 0
    private var outputUnitCount = 0
    private val layerSizes : MutableList<Int> = mutableListOf<Int>();

    private var weights : MutableList<DoubleMatrix> = mutableListOf<DoubleMatrix>();

    fun addInputLayer(numUnits : Int) : NNModel {
        inputUnitCount = numUnits
        layerSizes.add(0, numUnits)
        return this;
    }

    fun addHiddenLayer(numUnits: Int) : NNModel {
        layerSizes.add(numUnits)
        return this;
    }

    fun addOutputLayer(numUnits: Int) : NNModel {
        outputUnitCount = numUnits
        layerSizes.add(numUnits)
        return this;
    }

    fun create() : NNModel {
        for (i in 0 until layerCount()-1) {
            val sizeIn = layerSizeAt(i) + 1
            val sizeOut = layerSizeAt(i+1)
            weights.add(DoubleMatrix.rand(sizeOut, sizeIn))
        }
        return this
    }

    fun predict(input : DoubleArray) : DoubleArray{
        return predict(DoubleMatrix(input)).toArray();
    }

    fun predict(input : DoubleMatrix) : DoubleMatrix {
        var a = DoubleMatrix().copy(input).transpose()

        for (i in 0 until layerCount()-1) {
            a = a.addOnesToTheLeft()
            val z = a * weights[i].transpose()
            a = sigmoid(z)
        }
        return a
    }

    fun x(digit : Int) : DoubleMatrix{

        var a = DoubleMatrix.zeros(outputUnitCount).add(.000001);
        a.put(digit, 0.999999);

        for (i in weights.lastIndex downTo 0) {
            val z  = ln(a.div(1 - a))
            a = z.mmul(weights[i].transpose());
        }

        return a;
    }


    fun loadFromFile(fileName : String) : NNModel {
        val file = ByteBuffer.wrap(File(fileName).readBytes())
        file.order(ByteOrder.LITTLE_ENDIAN)

        val numLayers = file.int
        for (i in 0 until numLayers){
            layerSizes.add(file.int)
        }
        outputUnitCount = layerSizes.last()
        inputUnitCount = layerSizes.first()

        val synapseCount = file.int;
        val synapses = mutableListOf<Double>()
        for (i in 1..synapseCount) synapses.add(file.double)

        var index = 0;
        for (i in 0 until numLayers-1){
            val cols = layerSizeAt(i) +1
            val rows = layerSizeAt(i+1)
            weights.add(DoubleMatrix.zeros(rows, cols))

            for (y in 0 until rows) {
                for (x in 0 until cols) {
                    weights[i].put(y, x, synapses[index])
                    index++
                }
            }
        }

        return this
    }


    private fun sigmoid(z: DoubleMatrix): DoubleMatrix {
        return 1 / (1 + exp(z.neg()))
    }

    private fun layerSizeAt(position: Int) : Int{
        return layerSizes[position]
    }

    private fun layerCount() = layerSizes.size
}