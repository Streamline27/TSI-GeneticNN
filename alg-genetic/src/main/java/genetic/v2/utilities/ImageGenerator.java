package genetic.v2.utilities;

import genetic.v2.Const;
import genetic.v2.Gene;

import java.util.List;

public class ImageGenerator {

    private static final int WIDTH = Const.RECT_WIDTH;
    private static final int HEIGHT = Const.RECT_HEIGHT;

    public static double[] fromCode(List<Gene> code) {

        double[] img = new double[WIDTH * HEIGHT];

        for (Gene gene : code) {

            int x = gene.x();
            int y = gene.y();

            int index = x + y * WIDTH;

            color(img, index, 255);
            color(img, index + 1, 255);
            color(img, index - 1, 255);
            color(img, index - WIDTH, 255);
            color(img, index + WIDTH, 255);
        }

        return img;
    }


    private static void color(double[] array, int index, int color) {
        if (index < 0 || index >= array.length) return;
        array[index] = color;
    }

}
