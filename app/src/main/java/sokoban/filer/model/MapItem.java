package sokoban.filer.model;

public enum MapItem {
    WALL("#"),
    EMPTYGOAL("."),
    MANFLOOR("@"),
    MANGOAL("+"),
    BOXFLOOR("$"),
    BOXGOAL("*"),
    NEWLINE("|");

    public final String tile;

    private MapItem(String tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        return tile;
    }

}
