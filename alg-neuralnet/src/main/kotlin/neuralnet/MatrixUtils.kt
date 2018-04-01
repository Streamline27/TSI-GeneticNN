package neuralnet

import org.jblas.DoubleMatrix
import org.jblas.MatrixFunctions

operator fun DoubleMatrix.plus(other : DoubleMatrix) = this.add(other)
operator fun DoubleMatrix.minus(other : DoubleMatrix) = this.sub(other)
operator fun DoubleMatrix.times(other : DoubleMatrix) = this.mmul(other)
operator fun Int.div(matrix: DoubleMatrix) = DoubleMatrix.scalar(this.toDouble()).div(matrix)
operator fun Int.plus(matrix: DoubleMatrix) = matrix.add(this.toDouble())
operator fun Int.minus(matrix: DoubleMatrix) = matrix.add(-this.toDouble())

fun DoubleMatrix.trans() = this.transpose();
fun DoubleMatrix.addOnesToTheLeft() = DoubleMatrix.concatHorizontally(DoubleMatrix.ones(this.rows), this)

fun exp(matrix: DoubleMatrix) = MatrixFunctions.exp(matrix)
fun ln(matrix: DoubleMatrix) = MatrixFunctions.log(matrix)
