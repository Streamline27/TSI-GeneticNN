package genetic.v2;

import genetic.v2.utilities.RandomGen;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gene gene = (Gene) o;
        return Objects.equals(x, gene.x) &&
                Objects.equals(y, gene.y);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    public Integer sortKey() {
        return x*100+y;
    }

}
