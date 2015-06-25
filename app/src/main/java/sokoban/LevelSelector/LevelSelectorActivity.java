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

    //TODO fix bug - what if no item is selected?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        //make sure there is always at least one Maze in preferences
        if(!sharedPrefFiler.containsData()){
            sharedPrefFiler.exportMap("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n","01 Maze");
        }

        allKeys = sharedPrefFiler.loadAll();
        setContentView(R.layout.activity_level_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.map_item, allKeys);
        adapter.sort(String.CASE_INSENSITIVE_ORDER);
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                LevelSelectorActivity.this.selectMap(position);}
        });

        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true, false, true, true, true));
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

    private void selectMap(int position){
        gridview.requestFocusFromTouch();
        gridview.setSelection(position);
        ArrayAdapter myAdapter = (ArrayAdapter)gridview.getAdapter();
        this.selectedKey = myAdapter.getItem(this.selectedPos).toString();
        this.selectedPos = position;
    }

    public String getMapKey() {
        if(this.selectedKey == null){
            //inform the user they need to select a key
        }
        return this.selectedKey;
    }

    public void deleteMap(){
        //remove from persistan storage
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        sharedPrefFiler.removeData(this.selectedKey);
        //add here - change the thing in focus.
        this.selectMap(0);
        //remove textView from the gridview
        ArrayAdapter myAdapter = (ArrayAdapter) gridview.getAdapter();
        Object item = myAdapter.getItem(this.selectedPos);
        myAdapter.remove(item);
    }
}
