package sokoban.mainView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.mysokonabapplication.R;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameView.GameActivity;
import sokoban.levelBuilder.view.LevelBuilderActivity;

public class LevelOptionsActivity extends AppCompatActivity {
    //using SharedPreferences for storing data
    IFiler sharedPrefFiler;
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
    private String myKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_options);

        Intent intent = getIntent();
        this.myKey = intent.getStringExtra(LevelBuilderActivity.EXTRA_MESSAGE);
        TextView selectedLevel =  (TextView) findViewById(R.id.selectedName);
        selectedLevel.setText(this.myKey);

        //Create filer
        //TODO - utltimately this will be recieved in intent
        Context sokoContext = getApplicationContext();
        this.sharedPrefFiler = new SharedPreferencesFiler(sokoContext);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_options, menu);
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

    public void onClickEditLevel(View view){
        Intent intent = new Intent(this, LevelBuilderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, this.myKey);
        startActivity(intent);
    }

    public void onClickDeleteLevel(View view){
        this.sharedPrefFiler.removeData(this.myKey);
        this.myKey = null;
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }

    public void onClickPlayLevel(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, this.myKey);
        startActivity(intent);
    }
}
