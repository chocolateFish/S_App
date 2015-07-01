package sokoban.game.GameView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.AbstractFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameController;

//import com.example.user.mysokonabapplication.R;


public class GameActivity extends AppCompatActivity {
    private static final String STATE_MAZE = "mCurrentMaze";
        private MazeView myMaze;
        private GameController myController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        //TODO - get Maze from intent instead
        AbstractFiler myFiler = new SharedPreferencesFiler(this);

        //get key from LevelOptions Activity
        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelSelectorActivity.EXTRA_MESSAGE);
        //load the selected Maze
        String mazeStr;
        if(key != null){
            mazeStr = myFiler.importMap(key);
        }  else{  //if there is no key, load the default maze
            mazeStr = myFiler.importMap("01 Maze");
        }
        myController = new GameController(mazeStr);

        myMaze = new MazeView(this, myController);
        myController.setView(myMaze);
        setContentView(myMaze);
        SwipeDetector swipeDetector = new SwipeDetector(myController);
        myMaze.setOnTouchListener(swipeDetector);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        String myMazeStr = myController.getMazeString();
        savedInstanceState.putString(STATE_MAZE, myMazeStr);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onPause(){
        super.onPause();

    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
       String myMaze = savedInstanceState.getString(STATE_MAZE);
        myController.updatePositions(myMaze);
    }

}
