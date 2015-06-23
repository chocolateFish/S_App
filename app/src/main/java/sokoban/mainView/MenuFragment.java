package sokoban.mainView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.mysokonabapplication.R;

import sokoban.LevelSelector.LevelSelectorActivity;
import sokoban.levelBuilder.view.LevelBuilderActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements Button.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "buildLevelBtnVisible";
    private static final String ARG_PARAM2 = "selectLevelBtnVisible";
    private static final String ARG_PARAM3 = "exitBtnVisible";

    private boolean buildLevelBtnVisible;
    private boolean selectLevelBtnVisible;
    private boolean exitBtnVisible;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isBuilderBtnVisible Parameter 1.
     * @param isSelectorBtnVisible Parameter 2.
     * @param isExitBtnVisible Parameter 3.
     * @return A new instance of fragment MenuFragment.
     */
    public static MenuFragment newInstance(Boolean isBuilderBtnVisible, Boolean isSelectorBtnVisible, Boolean isExitBtnVisible) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isBuilderBtnVisible);
        args.putBoolean(ARG_PARAM2, isSelectorBtnVisible);
        args.putBoolean(ARG_PARAM3, isExitBtnVisible);
        fragment.setArguments(args);
        return fragment;
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            buildLevelBtnVisible = getArguments().getBoolean(ARG_PARAM1);
            selectLevelBtnVisible = getArguments().getBoolean(ARG_PARAM2);
            exitBtnVisible = getArguments().getBoolean(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        //Hide /show buttons based on parameters - not sure if this is the robust way of doing this
        if (buildLevelBtnVisible) {
            Button levelBuilderBtn = (Button) rootView.findViewById(R.id.levelBuilderBtn);
            levelBuilderBtn.setVisibility(View.VISIBLE);
            levelBuilderBtn.setOnClickListener(this);
        }
        if (selectLevelBtnVisible) {
            Button levelSelectorBtn = (Button) rootView.findViewById(R.id.levelSelectorBtn);
            levelSelectorBtn.setVisibility(View.VISIBLE);
            levelSelectorBtn.setOnClickListener(this);
        }

        if (exitBtnVisible) {
            Button exitBtn = (Button) rootView.findViewById(R.id.exitBtn);
            exitBtn.setVisibility(View.VISIBLE);
            exitBtn.setOnClickListener(this);
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.levelBuilderBtn:
                Intent builderIntent = new Intent(this.getActivity(), LevelBuilderActivity.class);
                startActivity(builderIntent);

                break;
            case R.id.levelSelectorBtn:
                Intent selectorIntent = new Intent(this.getActivity(), LevelSelectorActivity.class);
                startActivity(selectorIntent);
                break;
            case R.id.exitBtn:
                //
                break;
        }
    }
}
