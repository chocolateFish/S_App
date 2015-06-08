package sokoban.levelBuilder.model;
/*
Written by Nate from LevelBuilder group
 */
class LevelCell implements ILevelCell {

    private CellActor myActor = CellActor.NONE;
    private CellBackground myBackground = CellBackground.WALL;

    // Some constructors: we can construct a new cell directly from its string representation


    LevelCell() {}

    LevelCell(CellBackground background, CellActor actor) {
        setContents(background, actor);
    }


    LevelCell(String s) {
        setString(s);
    }



    @Override
    public CellActor getActor() {
        // TODO Auto-generated method stub
        return myActor;
    }

    @Override
    public CellBackground getBackground() {
        // TODO Auto-generated method stub
        return myBackground;
    }

    @Override
    public void setActor(CellActor actor) {
        // TODO Auto-generated method stub
        myActor = actor;

        enforceFlatness();
    }

    @Override
    public void setBackground(CellBackground background) {
        // TODO Auto-generated method stub
        myBackground = background;
        enforceFlatness();

    }

    private void enforceFlatness() {
        // Enforce that you can't put an actor on a non-flat background

        if (! myBackground.isFlat()) {
            myActor = CellActor.NONE;
        }
    }

    @Override
    public void setContents(CellBackground background, CellActor actor) {
        setBackground(background);
        setActor(actor);
    }

    @Override
    public String getString() {
        // This will return a one-character string showing the Sokoban Level Format symbol for this cell
        String s = " "; // Default to space (floor) if anything goes wrong

        switch (myBackground) {
            case WALL: s = "#"; break;
            case TARGET: switch (myActor) {
                case PLAYER: s = "+"; break;
                case CRATE: s = "*"; break;
                default: s = "."; break;
            }; break;
            case FLOOR: switch (myActor) {
                case PLAYER: s = "@"; break;
                case CRATE: s = "$"; break;
                default: s = "-"; break;
            }; break;
        }

        return s;
    }

    @Override
    public void setString(String s) {
        switch (s) {
            case "#": setContents(CellBackground.WALL, CellActor.NONE); break;
            case "+": setContents(CellBackground.TARGET, CellActor.PLAYER); break;
            case "*": setContents(CellBackground.TARGET, CellActor.CRATE); break;
            case ".": setContents(CellBackground.TARGET, CellActor.NONE); break;
            case "@": setContents(CellBackground.FLOOR, CellActor.PLAYER); break;
            case "$": setContents(CellBackground.FLOOR, CellActor.CRATE); break;

            case " ":
            case "-":
            case "_": setContents(CellBackground.FLOOR, CellActor.NONE); break;

            default: setContents(CellBackground.FLOOR, CellActor.NONE); break;
        }
    }

}