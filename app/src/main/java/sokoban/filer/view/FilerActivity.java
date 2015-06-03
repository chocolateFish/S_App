package sokoban.filer.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.user.mysokonabapplication.R;

import sokoban.filer.FilerController;

public class FilerActivity extends Activity {
    //using SahredPreferences for storing data
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    FilerController myController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filer);
        this.myController = new FilerController();
        //using SahredPreferences for storing data
        this.sharedPref = getPreferences(MODE_PRIVATE);
        this.editor = sharedPref.edit();
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
        String maze = saveLevelInput.getText().toString();
        String zippedMaze = this.myController.getZippedString(maze);

        editor.putString(key, zippedMaze);
        editor.commit();
        //TODO
        //clear input fields.

        //getshared preferences - will be in a different place later, but for now i need it here

        String defaultValue = "No Such Maze";
        String test = this.sharedPref.getString(key , defaultValue);
        CharSequence testString = this.myController.getUnZippedString(test);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, testString, duration);
        toast.show();
    }


}
