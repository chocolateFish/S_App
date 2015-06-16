package sokoban.LevelSelector;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.mysokonabapplication.R;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;

public class LevelSelectorActivity extends AppCompatActivity {
    IFiler sharedPrefFiler;
    String[] allKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create filer
        //TODO - utltimately this will be recieved in intent
        Context sokoContext = getApplicationContext();
        this.sharedPrefFiler = new SharedPreferencesFiler(sokoContext);
        this.allKeys = this.sharedPrefFiler.loadAll();

        setContentView(R.layout.activity_level_selector);
        displayAll();
    }


    public void displayAll(){
        StringBuilder sb = new StringBuilder();
        for (String key : this.allKeys){
            String value = this.sharedPrefFiler.importMap(key);
            sb.append(key).append(" - ").append(value).append('\n');
        }
        TextView allMazesText = (TextView) findViewById(R.id.allMazesText);
        CharSequence displayTxt = sb.toString();
        allMazesText.setText(displayTxt);
    }

}
