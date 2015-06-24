package sokoban.mainView;


import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.user.mysokonabapplication.R;

public class MainActivity extends AppCompatActivity implements MenuFragment.OnMenuInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Begin the transaction
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.menu_container, MenuFragment.newInstance(true, true, false, false , false));
        ft.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
    }

    public void deleteMap(){
        //
    }
    public String getMapKey (){
        return "";
    }
}
