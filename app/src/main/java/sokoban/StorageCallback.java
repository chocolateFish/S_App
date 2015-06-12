package sokoban;

import android.content.SharedPreferences;

import sokoban.filer.FileHandler;


/**
 * Created by User on 10/06/2015.
 */
public class StorageCallback {

    private StorageCallback(){
        super();
    }
    public static String getZippedString(String toZipStr){
        return FileHandler.zip(toZipStr);
    }

    public static String getUnZippedString(String toUnzipStr){
        return FileHandler.unZip(toUnzipStr);
    }
}
