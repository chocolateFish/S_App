package sokoban.game.GameView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameController;
import sokoban.mainView.LevelOptionsActivity;


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
        //TODO - utltimately this will be recieved in intent
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);

        //get key from LevelOptions Activity
        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelOptionsActivity.EXTRA_MESSAGE);
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
  /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
