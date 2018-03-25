package genetic.v2.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvParser {

    public double[] parse(String fileName) throws FileNotFoundException {
        File file= new File(fileName);

        // this gives you a 2-dimensional array of strings
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            String line= inputStream.next();
            String[] values = line.split(",");

            inputStream.close();

            double[] doubleValues = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                doubleValues[i] = Double.parseDouble(values[i]);
            }

            return doubleValues;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new double[1];
    }

}