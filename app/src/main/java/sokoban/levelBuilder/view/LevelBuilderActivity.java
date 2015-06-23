package sokoban.levelBuilder.view;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mysokonabapplication.R;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.levelBuilder.LevelBuilderController;
import sokoban.mainView.LevelOptionsActivity;
import sokoban.mainView.MenuFragment;

public class LevelBuilderActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";

    private IFiler sharedPrefFiler;
    LevelBuilderController myController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_builder);
        this.myController = new LevelBuilderController();

        //Create filer
        this.sharedPrefFiler = new SharedPreferencesFiler(this);

        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelBuilderActivity.EXTRA_MESSAGE);
        if (key != null) {
            String maze = this.sharedPrefFiler.importMap(key);
            EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
            EditText saveLevelInput = (EditText) findViewById(R.id.saveLevelInput);
            saveAsInput.setText(key);
            saveLevelInput.setText(maze);
        }

        //replace this with an onfocus changed listenr on the tes=xt view
        final Button checkKeyBtn = (Button) findViewById(R.id.checkKeyBtn);
        checkKeyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LevelBuilderActivity.this.checkKey();
            }
        });


        final Button saveLevelBtn = (Button) findViewById(R.id.saveLevelBtn);
        saveLevelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LevelBuilderActivity.this.saveLevelToKey();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first


    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
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

    private void saveLevelToKey() {
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
        this.sharedPrefFiler.exportMap(maze, key);


        Intent intent = new Intent(this, LevelOptionsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, key);
        startActivity(intent);
    }

    //ideally this would be in a TextWatcher listener - not attached to button.
    private void checkKey(){
        //Get the Key
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        String key = saveAsInput.getText().toString();
        CharSequence msgString = this.sharedPrefFiler.getKeyAvailability(key);

        Toast toast = Toast.makeText(this, msgString, Toast.LENGTH_LONG);
        toast.show();
    }



}
