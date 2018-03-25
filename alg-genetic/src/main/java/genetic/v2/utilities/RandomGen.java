package genetic.v2.utilities;

import genetic.v2.Const;
import genetic.v2.Gene;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomGen {

    private static Random random = new Random();

    public static int getInt(int bound) {
        return random.nextInt(bound);
    }

    public static int getInt(int from, int to) {
        return from + random.nextInt(to+1);
    }

    public static int getX() {
        return random.nextInt(Const.RECT_WIDTH);
    }

    public static int getY() {
        return random.nextInt(Const.RECT_HEIGHT);
    }

    public static String getName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = Const.GENE_NAME_LENGTH;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static Double getRandonDouble() {
        return random.nextDouble();
    }
}
