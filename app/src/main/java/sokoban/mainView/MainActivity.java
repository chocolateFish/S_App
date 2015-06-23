package sokoban.mainView;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.example.user.mysokonabapplication.R;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.levelBuilder.view.LevelBuilderActivity;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true, true, false));
        ft.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
    }

/*
    public void goToLevelBuilder(View view) {
        Intent intent = new Intent(this, LevelBuilderActivity.class);
        startActivity(intent);

    }

    public void goToLevelSelector(View view){
        Intent intent = new Intent(this, LevelSelectorActivity.class);
        startActivity(intent);
    }
    */



        public void onFragmentInteraction(Uri uri){
            //
    }

}
