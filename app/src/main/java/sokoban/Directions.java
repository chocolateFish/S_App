package sokoban;

/**
 * Created by User on 18/05/2015.
 */
public enum Directions {

    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    public final int horizontal;
    public final int vertical; // final can only be assigned once only

    Directions(int horizontal, int vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

}

