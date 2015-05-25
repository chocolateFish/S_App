package sokoban.game;

import sokoban.Directions;
import sokoban.game.View.MazeView;
import sokoban.game.gameModel.IMaze;
import sokoban.game.gameModel.Maze;
import sokoban.game.View.SwipeInterface;

/**
 * Created by User on 18/05/2015.
 */
public class GameController implements SwipeInterface, GetMazeInfoCallback, HasMovedCallback {
    //private Person person;
    private MazeView myView;
    private IMaze myModel;

    public GameController(){
        this.myModel = new Maze("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n", this);
        //this.myView = new MazeView(context);
        //this.myView.invalidate();
        //this.myView.register(this);
    }
    public GameController(MazeView myView){
        this.myView = myView;
        //this.person = new Person(1,1);
        this.myModel = new Maze("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n", this);
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
        //int manAcross = this.person.getAcross();
       // int manDown = this.person.getDown();
       // this.myView.setManPos(manAcross, manDown);
    }

    //TODO
    public void hasMoved(){
        this.myView.invalidate();
    }

}
