package genetic.utilities;

import genetic.Constants;

import java.util.Random;

public class RandomGen {

    private static Random random = new Random();

    public static double[] getRandomCode() {
        double[] code = new double[Constants.GENOME_LENGTH];
        for (int i = 0; i < Constants.GENOME_LENGTH; i++) {

            if (Constants.BLACK_AND_WHITE_MODE_ON) {
                code[i] = random.nextBoolean() ? Constants.COLOR_BLACK : Constants.COLOR_WHITE;
            }
            else {
                code[i] = random.nextInt(255);
            }
        }
        return code;
    }

    public static double getRandonDouble() {
        return random.nextDouble();
    }

    public static int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    public static int getRandomGene() {
        return random.nextInt(255);
    }

    public static String getRandomName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = Constants.GENE_NAME_LENGTH;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static int getCrossoverPoint() {
        return random.nextInt(Constants.GENOME_LENGTH);
    }

}
