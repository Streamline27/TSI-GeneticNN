package neuralnet

import org.junit.Test

import org.junit.Assert.*

class NNModelTest {

    @Test
    fun x() {

        var model = NNModel().loadFromFile("/Users/Vladislav/Development/Home/Java/TSI-GeneticNN/alg-genetic/src/test/resources/winston.nn");
        val res = model.x(3);

        println(res)

    }
}