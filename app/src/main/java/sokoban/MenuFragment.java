package sokoban;

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
import sokoban.game.GameView.GameActivity;
import sokoban.levelBuilder.view.LevelBuilderActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements Button.OnClickListener {
    public final static String EXTRA_MESSAGE = "SelectedLevelKey.MESSAGE";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "designBtnVisible";
    private static final String ARG_PARAM2 = "selectBtnVisible";
    private static final String ARG_PARAM3 = "playBtnVisible";
    private static final String ARG_PARAM4 = "editBtnVisible";
    private static final String ARG_PARAM5 = "deleteBtnVisible";

    private boolean designBtnVisible;
    private boolean selectBtnVisible;
    private boolean playBtnVisible;
    private boolean editBtnVisible;
    private boolean deleteBtnVisible;

    private OnMenuInteractionListener myListener;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param isDesignBtnVisible Parameter 1.
     * @param isSelectBtnVisible Parameter 2..
     * @param isPlayBtnVisible Parameter 3.
     * @param isEditBtnVisible Parameter 4.
     * @param isDeleteBtnVisible Parameter 5.
     * @return A new instance of fragment MenuFragment.
     */
    public static MenuFragment newInstance(Boolean isDesignBtnVisible, Boolean isSelectBtnVisible,
                                           Boolean isPlayBtnVisible, Boolean isEditBtnVisible,
                                           Boolean isDeleteBtnVisible) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isDesignBtnVisible);
        args.putBoolean(ARG_PARAM2, isSelectBtnVisible);
        args.putBoolean(ARG_PARAM3, isPlayBtnVisible);
        args.putBoolean(ARG_PARAM4, isEditBtnVisible);
        args.putBoolean(ARG_PARAM5, isDeleteBtnVisible);
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
            designBtnVisible = getArguments().getBoolean(ARG_PARAM1);
            selectBtnVisible = getArguments().getBoolean(ARG_PARAM2);
            playBtnVisible = getArguments().getBoolean(ARG_PARAM3);
            editBtnVisible = getArguments().getBoolean(ARG_PARAM4);
            deleteBtnVisible = getArguments().getBoolean(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        //Hide /show buttons based on parameters - not sure if this is the robust way of doing this
        //setOnclick listners
        if (designBtnVisible) {
            Button levelBuilderBtn = (Button) rootView.findViewById(R.id.levelBuilderBtn);
            levelBuilderBtn.setVisibility(View.VISIBLE);
            levelBuilderBtn.setOnClickListener(this);
        }
        if (selectBtnVisible) {
            Button levelSelectorBtn = (Button) rootView.findViewById(R.id.levelSelectorBtn);
            levelSelectorBtn.setVisibility(View.VISIBLE);
            levelSelectorBtn.setOnClickListener(this);
        }

        if (playBtnVisible) {
            Button playBtn = (Button) rootView.findViewById(R.id.playBtn);
            playBtn.setVisibility(View.VISIBLE);
            playBtn.setOnClickListener(this);
        }
        if (editBtnVisible) {
            Button editBtn = (Button) rootView.findViewById(R.id.editBtn);
            editBtn.setVisibility(View.VISIBLE);
            editBtn.setOnClickListener(this);
        }
        if (deleteBtnVisible) {
            Button deleteBtn = (Button) rootView.findViewById(R.id.deleteBtn);
            deleteBtn.setVisibility(View.VISIBLE);
            deleteBtn.setOnClickListener(this);
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            myListener = (OnMenuInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public void onClick(View view){
        String selectedKey = myListener.getMapKey();
        switch(view.getId()){
            case R.id.levelBuilderBtn:
                Intent builderIntent = new Intent(this.getActivity(), LevelBuilderActivity.class);
                startActivity(builderIntent);
                break;
            case R.id.levelSelectorBtn: default:
                Intent selectorIntent = new Intent(this.getActivity(), LevelSelectorActivity.class);
                startActivity(selectorIntent);
                break;
            case R.id.playBtn:
                Intent playIntent = new Intent(this.getActivity(), GameActivity.class);
                playIntent.putExtra(EXTRA_MESSAGE, selectedKey);
                startActivity(playIntent);
                break;
            case R.id.editBtn:
                Intent editIntent = new Intent(this.getActivity(), LevelBuilderActivity.class);
                editIntent.putExtra(EXTRA_MESSAGE, selectedKey);
                startActivity(editIntent);
                break;
            case R.id.deleteBtn:
                myListener.deleteMap();
                break;
        }
    }


    // Container Activity must implement this interface
    public interface OnMenuInteractionListener {
       String getMapKey();
        void deleteMap();
    }
}
