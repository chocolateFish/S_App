package sokoban.levelBuilder.model;

public enum MapItem {
    WALL("#"),
    EMPTY_GOAL("."),
    MAN_FLOOR("@"),
    MAN_GOAL("+"),
    BOX_FLOOR("$"),
    BOX_GOAL("*"),
    NEW_LINE("|");

    public final String tile;

    private MapItem(String tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        return tile;
    }

}
