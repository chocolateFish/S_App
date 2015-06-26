package sokoban.LevelSelector;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mysokonabapplication.R;

import java.util.List;

import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.MenuFragment;

public class LevelSelectorActivity extends AppCompatActivity implements MenuFragment.OnMenuInteractionListener{
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
    private GridView gridview;
    private String selectedKey;
    private int selectedPos;
   // IFiler sharedPrefFiler;
   List<String> allKeys;

    //TODO fix auto select on load.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        //make sure there is at least one value in shared preferences
        if(!sharedPrefFiler.containsData()){
            sharedPrefFiler.exportMap("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n", "01 Maze");
        }
        //populate list
        allKeys = sharedPrefFiler.loadAll();
        //draw the view
        setContentView(R.layout.activity_level_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.map_item, allKeys);
        adapter.sort(String.CASE_INSENSITIVE_ORDER);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                LevelSelectorActivity.this.selectedPos = position;
                LevelSelectorActivity.this.selectedKey = ((TextView) v).getText().toString();
                gridview.requestFocusFromTouch();
                gridview.setSelection(position);
            }
        });
        //select item at first position
        this.selectMap(gridview.getFirstVisiblePosition());

        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true));
        ft.commit();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        this.selectMap(gridview.getFirstVisiblePosition());
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    private void selectMap(int position){
        this.selectedPos = position;
        ArrayAdapter myAdapter = (ArrayAdapter)gridview.getAdapter();
        this.selectedKey = myAdapter.getItem(this.selectedPos).toString();
        gridview.requestFocusFromTouch();
        gridview.setSelection(position);

    }

    public String getMapKey() {
        if(this.selectedKey == null){
            //TODO

        }
        return this.selectedKey;
    }

    public void deleteMap(){
        //remove from persistant storage
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        sharedPrefFiler.removeData(this.selectedKey);

        //remove textView from the gridview
        ArrayAdapter<String> myAdapter = (ArrayAdapter)gridview.getAdapter();
        myAdapter.remove(myAdapter.getItem(this.selectedPos));
        this.selectMap(gridview.getFirstVisiblePosition());
    }
}
