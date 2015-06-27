package sokoban.levelBuilder.view;




import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.mysokonabapplication.R;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.levelBuilder.LevelBuilderController;
import sokoban.levelBuilder.model.ILevelMap;
import sokoban.levelBuilder.model.LevelMap;


public class LevelBuilderActivity extends AppCompatActivity{
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";

    private IFiler sharedPrefFiler;
    LevelBuilderController myController;
    //private ILevelMap myMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_builder);
        this.myController = new LevelBuilderController();
        //this.myMap = new LevelMap();

        //Create filer
        this.sharedPrefFiler = new SharedPreferencesFiler(this);

      //change this to get the height and width of the maze.
        Intent intent = getIntent();
        String key = intent.getStringExtra(LevelSelectorActivity.EXTRA_MESSAGE);
        if (key != null) {
            String maze = this.sharedPrefFiler.importMap(key);
            EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
            EditText levelInput = (EditText) findViewById(R.id.levelInput);
            saveAsInput.setText(key);
            levelInput.setText(maze);
        }

/*
        //replace this with an onfocus changed listenr on the tes=xt view
        final Button buildMapBtn = (Button) findViewById(R.id.buildMapBtn);
        buildMapBtn .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get the Key
                EditText widthInput = (EditText) findViewById(R.id.widthInput);
                int width = Integer.parseInt(widthInput.getText().toString());
                EditText heightInput = (EditText) findViewById(R.id.heightInput);
                int height = Integer.parseInt(heightInput.getText().toString());
                LevelBuilderActivity.this.myMap.setSize(width, height);
                //resize
                EditText levelInput = (EditText) findViewById(R.id.levelInput);
                //set max width - this isn't right, but I donw't know howto doit right
                levelInput.setEms(width);
                levelInput.setLines(height);
            }
        });
*/

        //replace this with validation within the saveBtn code
        final Button checkKeyBtn = (Button) findViewById(R.id.checkKeyBtn);
        checkKeyBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get the Key
                EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
                String key = saveAsInput.getText().toString();

                CharSequence msgString = LevelBuilderActivity.this.sharedPrefFiler.getKeyAvailability(key);

                Toast toast = Toast.makeText(LevelBuilderActivity.this, msgString, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        final Button saveLevelBtn = (Button) findViewById(R.id.saveLevelBtn);
        saveLevelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LevelBuilderActivity.this.saveLevelToKey();
            }
        });

 /*       EditText levelInput = (EditText) findViewById(R.id.levelInput);
        InputFilter charFilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.toString(source.charAt(i)).equals("@") ||
                            !Character.toString(source.charAt(i)).equals("#") ||
                            !Character.toString(source.charAt(i)).equals("$")||
                            !Character.toString(source.charAt(i)).equals("-")||
                            !Character.toString(source.charAt(i)).equals(".")||
                            !Character.toString(source.charAt(i)).equals("+")) {
                        return "";
                    }
                }
                return null;
            }
        };

        levelInput.setFilters(new InputFilter[]{charFilter});
        */

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first


    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first
    }

    private void saveLevelToKey() {
        //Get the Key
        EditText saveAsInput= (EditText) findViewById(R.id.saveAsInput);
        String key = saveAsInput.getText().toString();
        //get the maze as a string
        EditText levelInput = (EditText) findViewById(R.id.levelInput);
        String buildStr = levelInput.getText().toString();

        //LevelBuilderCallback - to build level
        //  this will actually happen in a seperate place in the actual builder
        this.myController.buildLevel(buildStr);

        //then get the string back out
        String maze = this.myController.levelToString();
        this.sharedPrefFiler.exportMap(maze, key);


        Intent intent = new Intent(this, LevelSelectorActivity.class);
        intent.putExtra(EXTRA_MESSAGE, key);
        startActivity(intent);
    }

}
