package sokoban.game;

public interface GetMazeInfoCallback {
    String getMazeString();
    BlockTypes getBlockType(int across, int down);
    int getMazeHeight ();
    int getMazeWidth ();
    void updatePositions(String currentMaze);
}
