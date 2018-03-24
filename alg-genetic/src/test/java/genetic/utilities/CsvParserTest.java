package genetic.utilities;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class CsvParserTest {

    @Test
    public void test() throws FileNotFoundException {
        CsvParser parser = new CsvParser();

        double[] d = parser.parse("number3.csv");

    }

}