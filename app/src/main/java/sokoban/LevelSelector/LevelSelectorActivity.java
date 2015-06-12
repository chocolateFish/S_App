package sokoban.LevelSelector;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.user.mysokonabapplication.R;

import java.util.Map;

import sokoban.IFileHandlerCallback;
import sokoban.StorageCallback;

public class LevelSelectorActivity extends AppCompatActivity {
    //using SharedPreferences for storing data
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using SahredPreferences for storing data
        Context sokoContext = getApplicationContext();
        this.sharedPref = sokoContext.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        setContentView(R.layout.activity_level_selector);
        displayAll();
    }


    public void displayAll(){
        StringBuilder sb = new StringBuilder();
        Map<String, ?> allMazes = this.sharedPref.getAll();
        for (Map.Entry<String, ?> e : allMazes.entrySet()){
            String value = e.getValue().toString();
            ////TODO - register?

            String unzippedValue = StorageCallback.getUnZippedString(value);
            sb.append(e.getKey()).append('-').append(unzippedValue).append('\n');
            // sb.append(e.getKey() + '-' + this.myController.getUnZippedString(e.getValue().toString()) + '\n');
        }
        TextView allMazesText = (TextView) findViewById(R.id.allMazesText);
        CharSequence displayTxt = sb.toString();
        allMazesText.setText(displayTxt);
    }

}
