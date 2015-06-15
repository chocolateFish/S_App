package sokoban.mainView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.mysokonabapplication.R;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.levelBuilder.view.LevelBuilderActivity;

public class MainActivity extends AppCompatActivity {
    // class variable because it will ultimately be passes around using intent
    private IFiler sharedPrefFiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context sokoContext = getApplicationContext();
        this.sharedPrefFiler = new SharedPreferencesFiler(sokoContext);
       // Button gameBtn = (Button) findViewById(R.id.gameBtn);
       // Button levelSelector = (Button) findViewById(R.id.levelSelectorBtn);
        if(this.sharedPrefFiler.containsData()){
            //gameBtn.setVisibility(View.VISIBLE);
            findViewById(R.id.gameBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.levelSelectorBtn).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToLevelBuilder(View view) {
        Intent intent = new Intent(this, LevelBuilderActivity.class);
        startActivity(intent);

    }

    public void goToLevelSelector(View view){
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }
}
