package genetic.v2;

import genetic.v2.utilities.RandomGen;

public class Gene {

    private final Integer x;
    private final Integer y;

    public Gene(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer x() {
        return x;
    }

    public Integer y() {
        return y;
    }

    public static Gene createRandom() {
        int x = RandomGen.getX();
        int y = RandomGen.getY();

        return new Gene(x, y);
    }

}
