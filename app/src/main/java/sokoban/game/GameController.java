package sokoban.game;

import sokoban.Directions;
import sokoban.game.View.MazeView;
import sokoban.game.gameModel.Maze;
import sokoban.game.View.SwipeInterface;

/**
 * Created by User on 18/05/2015.
 */
public class GameController implements SwipeInterface, GetMazeInfoCallback {
    //private Person person;
    private MazeView myView;
    private Maze myModel;

    public GameController(){

        //this.person = new Person(1,1);
        this.myModel = new Maze("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n");
        //this.myView = new MazeView(context);
        //this.myView.invalidate();
        //this.myView.register(this);
    }
    public GameController(MazeView myView){
        this.myView = myView;
        //this.person = new Person(1,1);
        this.myModel = new Maze("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n");
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
       // this.person.move(Directions.LEFT);
        //int manAcross = this.person.getAcross();
        //int manDown = this.person.getDown();
       // this.myView.setManPos(manAcross, manDown);
        this.myView.invalidate();

    }

    public void leftToRight(){
        this.myModel.playTurn(Directions.RIGHT);
        //this.person.move(Directions.RIGHT);
       // int manAcross = this.person.getAcross();
       // int manDown = this.person.getDown();
       // this.myView.setManPos(manAcross, manDown);
        this.myView.invalidate();
    }

    public void topToBottom(){
        this.myModel.playTurn(Directions.DOWN);
       // int manAcross = this.person.getAcross();
        //int manDown = this.person.getDown();
        //this.myView.setManPos(manAcross, manDown);
        this.myView.invalidate();
    }

    public void bottomToTop(){
        this.myModel.playTurn(Directions.UP);
        //int manAcross = this.person.getAcross();
       // int manDown = this.person.getDown();
       // this.myView.setManPos(manAcross, manDown);
        this.myView.invalidate();
    }

}
