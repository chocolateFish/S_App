package sokoban.levelBuilder.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.mysokonabapplication.R;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sokoban.levelBuilder.LevelBuilderController;

public class LevelBuilderActivity extends Activity {
    //using SahredPreferences for storing data
    SharedPreferences sharedPref;
    //TODO - Interface?? - for callbacks. HowTo??
    LevelBuilderController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filer);
        this.myController = new LevelBuilderController();
        //using SahredPreferences for storing data
        this.sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), this.MODE_PRIVATE);

    }

  //  @Override
   // public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
  //      getMenuInflater().inflate(R.menu.menu_main, menu);
  //      return true;
  //  }

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

    public void saveLevelToKey(View view) {
        //Get the Key
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        String key = saveAsInput.getText().toString();

        EditText saveLevelInput = (EditText) findViewById(R.id.saveLevelInput);
        String buildStr = saveLevelInput.getText().toString();
        //LevelBuilderCallback
        this.myController.buildLevel(buildStr);
        String maze = this.myController.levelToString();
        //FileHandlerCallback
        String zippedMaze = this.myController.getZippedString(maze);
        SharedPreferences.Editor editor  = sharedPref.edit();
        editor.putString(key, zippedMaze);
        editor.commit();
        //TODO
        //clear input fields.


        //getshared preferences - will be in a different place later, but for now i need it here

        String defaultValue = "No Such Maze";
        String test = this.sharedPref.getString(key , defaultValue);
        //FileHandlerCallback
        CharSequence testString = this.myController.getUnZippedString(test);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, testString, duration);
        toast.show();
    }

    public void displayAll(View view){
        String defaultValue = "No Such Maze";
        Map<String, ?> allMazes = this.sharedPref.getAll();
        //FileHandlerCallback
        Set keys = allMazes.keySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> e : allMazes.entrySet()) {

            //to get key //e.getKey();
            //and to get value //e.getValue();

        }

        /*
        for (Iterator i = keys.iterator(); i.hasNext();){
            String key = (String) i.next();
            String value = (String) allMazes.get(key);
            textview.setText(key + " = " + value);
        }
        */

        TextView allMazesText= (TextView) findViewById(R.id.allMazesText);

        //CharSequence testString = this.myController.getUnZippedString(test);
        //int duration = Toast.LENGTH_SHORT;
       // Toast toast = Toast.makeText(this, testString, duration);
       // toast.show();
    }

}
