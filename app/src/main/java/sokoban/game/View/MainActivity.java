package sokoban.game.View;


import android.app.Activity;
import android.os.Bundle;
import sokoban.game.GameController;
//import sokoban.game.GetMazeInfoCallback;

//import android.view.Menu;
//import android.view.MenuItem;


public class MainActivity extends Activity{
    MazeView myMaze;
    GameController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         myController = new GameController();
        //GetMazeInfoCallback callback;
        //callback = myGameController;

        myMaze = new MazeView(this, myController);
        myController.setView(myMaze);
        //Display display = getWindowManager().getDefaultDisplay();
        //Point size = new Point();
        //display.getSize(size);
        // myMaze.setBlockLength(size.x, size.y);
        //myMaze.setMazeBounds(size.x, size.y);
        setContentView(myMaze);

        SwipeDetector swipeDetector = new SwipeDetector(myController, this);
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
