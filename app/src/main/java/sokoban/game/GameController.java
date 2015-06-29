package sokoban.game;

import sokoban.Directions;
import sokoban.game.GameView.MazeView;
import sokoban.game.model.IMaze;
import sokoban.game.model.Maze;
import sokoban.game.GameView.SwipeInterface;

/**
 * Created by User on 18/05/2015.
 */
public class GameController implements SwipeInterface, GetMazeInfoCallback, HasMovedCallback {
    //private Person person;
    private MazeView myView;
    private IMaze myModel;

    public GameController(String mazeString){
        this.myModel = new Maze(mazeString, this);
        //this.myView = new MazeView(context);
        //this.myView.invalidate();
        //this.myView.register(this);
    }
    public GameController(MazeView myView, String mazeString){
        this.myView = myView;
        //this.person = new Person(1,1);
        this.myModel = new Maze(mazeString, this);
        //this.myView = new MazeView(context);
        this.myView.invalidate();
        this.myView.register(this);
    }

    public void setView(MazeView myView) {
        this.myView = myView;
    }

    public BlockTypes  getBlockType(int across, int down){
       return this.myModel.whoIsAt(across, down);
    }

    public int getMazeHeight (){
       return this.myModel.getHeight();
    }

    public int getMazeWidth (){
        return this.myModel.getWidth();
    }

    public void rightToLeft(){
        this.myModel.playTurn(Directions.LEFT);
    }

    public void leftToRight(){
        this.myModel.playTurn(Directions.RIGHT);
    }

    public void topToBottom(){
        this.myModel.playTurn(Directions.DOWN);
    }

    public void bottomToTop(){
        this.myModel.playTurn(Directions.UP);
    }

    //TODO
    public void hasMoved(){
        this.myView.invalidate();
    }

}
