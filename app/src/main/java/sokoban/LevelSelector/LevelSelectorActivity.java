package sokoban.LevelSelector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.user.mysokonabapplication.R;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.mainView.LevelOptionsActivity;

public class LevelSelectorActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
   // IFiler sharedPrefFiler;
    //String[] allKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create filer
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        String [] allKeys = sharedPrefFiler.loadAll();

        setContentView(R.layout.activity_level_selector);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new CustomGrid(this, allKeys));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Adapter myAdapter = parent.getAdapter();
                String key = myAdapter.getItem(position).toString();

                Intent intent = new Intent(LevelSelectorActivity.this, LevelOptionsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, key);
                startActivity(intent);
            }
        });
    }


   /* public void displayAll(){
        StringBuilder sb = new StringBuilder();
        for (String key : this.allKeys){
            String value = this.sharedPrefFiler.importMap(key);
            sb.append(key).append(" - ").append(value).append('\n');
        }
        TextView allMazesText = (TextView) findViewById(R.id.allMazesText);
        CharSequence displayTxt = sb.toString();
        allMazesText.setText(displayTxt);
    }
    */

}
