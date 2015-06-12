package sokoban.levelBuilder.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mysokonabapplication.R;

import sokoban.StorageCallback;
import sokoban.levelBuilder.LevelBuilderController;
import sokoban.mainView.LevelOptionsActivity;

public class LevelBuilderActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
    //using SahredPreferences for storing data
    SharedPreferences sharedPref;
    LevelBuilderController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_builder);
        this.myController = new LevelBuilderController();

        //using SahredPreferences for storing data
        Context sokoContext = getApplicationContext();
        this.sharedPref = sokoContext.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelBuilderActivity.EXTRA_MESSAGE);
        if (key != null) {
            this.loadSelected(key);
        }
    }

   /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Inflate the menu; this adds items to the action bar if it is present.
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
    private void loadSelected(String key){
        String mazeZip = this.sharedPref.getString(key, "no such maze");
        String maze = StorageCallback.getUnZippedString(mazeZip);
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        EditText saveLevelInput = (EditText) findViewById(R.id.saveLevelInput);
        saveAsInput.setText(key);
        saveLevelInput.setText(maze);
    }

    public void saveLevelToKey(View view) {
        //Get the Key
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        String key = saveAsInput.getText().toString();
        //get the maze as a string
        EditText saveLevelInput = (EditText) findViewById(R.id.saveLevelInput);
        String buildStr = saveLevelInput.getText().toString();

        //LevelBuilderCallback - to build level
        //  this will actually happen in a seperate place in the actual builder
        this.myController.buildLevel(buildStr);

        //then get the string back out
        String maze = this.myController.levelToString();
        //FileHandlerCallback- zip the string
        String zippedMaze = StorageCallback.getZippedString(maze);
        SharedPreferences.Editor editor  = sharedPref.edit();
        editor.putString(key, zippedMaze);
        editor.apply();
       // editor.commit();

        Intent intent = new Intent(this, LevelOptionsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, key);
        startActivity(intent);
    }

    //ideally this would be in a TextWatcher listener - not attached to button.
    public void checkKey(View view ){
        //Get the Key
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        String key = saveAsInput.getText().toString();
        CharSequence msgString;
        if (this.sharedPref.contains(key)){
            String existingMaze = StorageCallback.getUnZippedString(this.sharedPref.getString(key, "no such maze"));
            msgString = existingMaze + " exists at key:" + key + "\nProceed to override, or use a different key";

        } else {
            msgString ="Key is free.";
        }
        Toast toast = Toast.makeText(this, msgString, Toast.LENGTH_LONG);
        toast.show();
    }

    public void	onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
        //do view. - append text

    }
}
