package sokoban.levelBuilder.model;
/*
Written by Nate from LevelBuilder group
 */
public interface ILevelMap {
    void setString(String s);
    String getString();
    int getRows();
    int getCols();
    ILevelCell getCell(int x, int y);
    void setSize(int cols, int rows);
}
