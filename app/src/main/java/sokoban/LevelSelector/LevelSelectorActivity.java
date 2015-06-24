package sokoban.LevelSelector;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.user.mysokonabapplication.R;
import sokoban.filer.IFiler;
import sokoban.filer.SharedPreferencesFiler;
import sokoban.game.GameView.GameActivity;
import sokoban.levelBuilder.view.LevelBuilderActivity;
import sokoban.mainView.LevelOptionsActivity;
import sokoban.mainView.MenuFragment;

public class LevelSelectorActivity extends AppCompatActivity implements MenuFragment.OnMenuInteractionListener{
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";
    private String selectedKey;
   // IFiler sharedPrefFiler;
    //String[] allKeys;

    //TODO fix bug - what if no item is selected?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Create filer
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        //make sure theere is always at least one Maze in preferences
        if(!sharedPrefFiler.containsData()){
            sharedPrefFiler.exportMap("#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n","01 Maze");
        }

        String [] allKeys = sharedPrefFiler.loadAll();
        setContentView(R.layout.activity_level_selector);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new CustomGrid(this, allKeys));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Adapter myAdapter = parent.getAdapter();
                LevelSelectorActivity.this.selectedKey = myAdapter.getItem(position).toString();
            }
        });

        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true, false, true, true, true));
        ft.commit();
    }

    //TODO - so there is never an unselected Item
    public void setDefaultSelected(){
        //get the gridView adapter

    }


    public String getMapKey() {
        if(this.selectedKey == null){
            //inform the user they need to select a key
        }
        return this.selectedKey;
    }

    public void deleteMap(){
        IFiler sharedPrefFiler = new SharedPreferencesFiler(this);
        sharedPrefFiler.removeData(this.selectedKey);
        this.selectedKey = null;
    }


}
