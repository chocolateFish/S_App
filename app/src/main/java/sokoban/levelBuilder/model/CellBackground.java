package sokoban.levelBuilder.model;
/*
Written by Nate from LevelBuilder group
 */
public enum CellBackground {

    // The CellBackground enum type has a boolean to say if it's 'flat'
    // Only flat backgrounds can have actors

    WALL (false), FLOOR (true), TARGET (true);

    private final boolean flat;

    private CellBackground(boolean newFlat) {
        flat = newFlat;
    }
    public boolean isFlat() {
        return flat;
    }

}
