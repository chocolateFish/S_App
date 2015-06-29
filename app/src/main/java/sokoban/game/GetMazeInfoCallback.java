package sokoban.game;


/**
 * Created by User on 19/05/2015.
 */
public interface GetMazeInfoCallback {
    String getMazeString();
    BlockTypes getBlockType(int across, int down);
    int getMazeHeight ();
    int getMazeWidth ();
}
