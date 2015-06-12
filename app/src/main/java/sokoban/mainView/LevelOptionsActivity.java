package sokoban.mainView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.mysokonabapplication.R;

import sokoban.levelBuilder.view.LevelBuilderActivity;

public class LevelOptionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_options);

        Intent intent = getIntent();
        String message = intent.getStringExtra(LevelBuilderActivity.EXTRA_MESSAGE);
        TextView selectedLevel =  (TextView) findViewById(R.id.selectedName);
        selectedLevel.setText(message);
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

    public void doEditLevel(View view){
        Intent intent = new Intent(this, LevelBuilderActivity.class);

        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
    }
}
