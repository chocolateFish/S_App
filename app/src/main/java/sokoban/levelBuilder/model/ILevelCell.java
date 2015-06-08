package sokoban.levelBuilder.model;

/*
Written by Nate from LevelBuilder group
 */
public interface ILevelCell {
    CellActor getActor();
    CellBackground getBackground();
    void setActor(CellActor actor);
    void setBackground(CellBackground background);
    void setContents(CellBackground background, CellActor actor);
    String getString();
    void setString(String s);
}
