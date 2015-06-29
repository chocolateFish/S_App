package sokoban.filer;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class SharedPreferencesFiler extends FileHandler {

    private static SharedPreferences sharedPref;
    private static final String PREFERENCE_NAME = "AllMazes";
    private static final String DEFAULT_MAP = "#######\n#.....#\n#--.--#\n#$-@$-#\n#.$$$.#\n#-----#\n#######\n";
    private static final String DEFAULT_KEY = "01 Maze";

    public SharedPreferencesFiler(Context mContext){
        super();
        sharedPref = mContext.getSharedPreferences(
                SharedPreferencesFiler.PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.loadDefault();
    }

    private void loadDefault(){
        if (!this.containsData()){
            this.exportMap(SharedPreferencesFiler.DEFAULT_MAP, SharedPreferencesFiler.DEFAULT_KEY);
        }
    }

    @Override
      public String importMap(String key) {
        String zippedStr = sharedPref.getString(key, SharedPreferencesFiler.DEFAULT_MAP);
        return this.unZip(zippedStr);
    }

    @Override
    public void exportMap(String map, String key){
        String zippedMaze = this.zip(map);
        SharedPreferences.Editor editor  = sharedPref.edit();
        editor.putString(key, zippedMaze);
        editor.apply();
        // editor.commit();
    }

    public List<String> loadAll(){
        Set<String> allKeysSet = sharedPref.getAll().keySet();
        return new ArrayList<String>(allKeysSet);
    }

    public String getKeyAvailability(String key){
        if (sharedPref.contains(key)){
            String existingMaze = this.importMap(key);
            return existingMaze + " exists at key: " + key + "\n Proceed to override, or use a different key";
        } else {
            return "Key is free.";
        }

    }

    public boolean containsData(){
        return sharedPref.getAll().size() > 0;
    }

    public void removeData(String key){
        SharedPreferences.Editor editor  = sharedPref.edit();
        editor.remove(key);
        editor.commit();
        this.loadDefault();
    }


}
