package sokoban;

import android.content.SharedPreferences;

import sokoban.filer.FileHandler;
import sokoban.filer.IFiler;

/**
 * Created by User on 10/06/2015.
 */
public class StorageCallback implements IFileHandlerCallback {
    IFiler myFiler;
    public StorageCallback(){
        myFiler = new FileHandler();
    }
    public String getZippedString(String toZipStr){
        return this.myFiler.zip(toZipStr);
    }

    public String getUnZippedString(String toUnzipStr){
        return this.myFiler.unZip(toUnzipStr);
    }
}
