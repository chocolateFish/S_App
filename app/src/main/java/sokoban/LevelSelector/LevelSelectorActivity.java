package sokoban.LevelSelector;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.user.mysokonabapplication.R;
import java.util.Map;

public class LevelSelectorActivity extends ListActivity {
    //using SharedPreferences for storing data
    SharedPreferences sharedPref;
   //Map<key, value>
    Map<String, ?> mazesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //using SahredPreferences for storing data
        this.sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), this.MODE_PRIVATE);

        setContentView(R.layout.activity_level_selector);
    }
    //@Override
   // public void onListItemClick(...) {
    //    // Do something when a list item is clicked
   // }

    private void makeList(){
        this.mazesList = this.sharedPref.getAll();
    }
}
