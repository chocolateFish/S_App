package sokoban.LevelSelector;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import com.example.user.mysokonabapplication.R;

import java.util.List;

import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.MenuFragment;

public class LevelSelectorActivity extends AppCompatActivity implements MenuFragment.OnMenuInteractionListener{
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
    private GridView gridview;
    private String selectedKey;
    private IFiler sharedPrefFiler;

    //TODO fix auto select on load.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        sharedPrefFiler = new SharedPreferencesFiler(this);

        //make sure there is at least one value in shared preferences
        if(!sharedPrefFiler.containsData()){
            sharedPrefFiler.exportMap("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n", "01 Maze");
        }

        //populate list
        List<String> allKeys;allKeys = sharedPrefFiler.loadAll();
        //draw the view
        setContentView(R.layout.activity_level_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.map_item_checkable, allKeys);
        adapter.sort(String.CASE_INSENSITIVE_ORDER);
        gridview = (GridView) findViewById(R.id.gridview);

        gridview.setAdapter(adapter);
        gridview.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                LevelSelectorActivity.this.selectedKey = ((CheckedTextView) v).getText().toString();
                gridview.setItemChecked(position, true);

                //gridview.requestFocusFromTouch();
                //gridview.setSelection(position);
            }
        });

        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true));
        ft.commit();
    }

    @Override
    public void onStart(){
        super.onStart();
        this.selectMap(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        this.selectMap(0);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    private void selectMap(final int position){
        ArrayAdapter myAdapter = (ArrayAdapter)gridview.getAdapter();
        this.selectedKey = myAdapter.getItem(position).toString();
        gridview.setItemChecked(position, true);
       // gridview.requestFocusFromTouch();
        //gridview.setSelection(position);
    }

    public String getMapKey() {
        return this.selectedKey;
    }

    public void onClickDelete(){
        //remove from persistant storage
        sharedPrefFiler = new SharedPreferencesFiler(this);
        sharedPrefFiler.removeData(this.selectedKey);

        //update Array Adapter
        ((ArrayAdapter) gridview.getAdapter()).notifyDataSetChanged();
        this.selectMap(0);
    }
}
