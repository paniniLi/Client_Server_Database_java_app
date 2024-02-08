package MusicBand;

import java.io.Serializable;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Integer x;
    private long y;

    public Coordinates (Integer x) {
        this.x= x;
    }

    public Coordinates (Integer x, long y) {
        this.x = x; this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + this.x + "; " +
                "y: " + this.y;
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        return (int) (((long) this.x *this.x+this.y*this.y) - ((long) coordinates.x *coordinates.x+coordinates.y*coordinates.y));
    }
}