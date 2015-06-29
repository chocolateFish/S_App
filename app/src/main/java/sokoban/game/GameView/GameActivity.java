package sokoban.game.GameView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameController;



//import android.widget.TextView;

//import com.example.user.mysokonabapplication.R;


public class GameActivity extends AppCompatActivity {
    private static final String MY_PREFERENCE_NAME = "CurrentMaze";
        private MazeView myMaze;
        private GameController myController;
        private String myMazeStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        //TODO - get Maze from intent instead
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);

        //get key from LevelOptions Activity
        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelSelectorActivity.EXTRA_MESSAGE);
        //load the selected Maze
        String mazeStr;
        if(key != null){
            mazeStr = sharedPrefFiler.importMap(key);

        }  else{  //if there is no key, load the default maze
            mazeStr = sharedPrefFiler.importMap("01 Maze");
        }
        myController = new GameController(mazeStr);

        myMaze = new MazeView(this, myController);
        myController.setView(myMaze);
        setContentView(myMaze);

        SwipeDetector swipeDetector = new SwipeDetector(myController);
        myMaze.setOnTouchListener(swipeDetector);
    }

    @Override
    public void onPause(){
        //save the maze as a string - this would be more complicated if the model
        //included player info or stuff like that
        SharedPreferences.Editor editor  = this.getSharedPreferences(
                GameActivity.MY_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        String myMazeStr = myController.getMazeString();
        editor.putString("GameActivity",myMazeStr);
        editor.apply();
        this.myMaze = null;
        super.onPause();

    }

    @Override
    public void onResume(){
        //set the current view
        SharedPreferences myPref  = this.getSharedPreferences(
                GameActivity.MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
        //second value is a default map
        String myMaze =  myPref.getString("GameActivity", "#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n");
        myController.setMyModel(myMaze);
        super.onResume();

    }

}
