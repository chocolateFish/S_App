package sokoban.game.GameView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameController;


//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;

//import com.example.user.mysokonabapplication.R;


public class GameActivity extends AppCompatActivity {
        MazeView myMaze;
        GameController myController;

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

}
