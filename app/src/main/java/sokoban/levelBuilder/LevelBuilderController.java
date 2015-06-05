package sokoban.levelBuilder;

import sokoban.filer.FileHandler;
import sokoban.filer.IFiler;

public class LevelBuilderController implements IFileHandlerCallback {
    IFiler myFiler;

    public LevelBuilderController(){
        this.myFiler = new FileHandler();
    }

    public String getZippedString(String toZipStr){
        return this.myFiler.zip(toZipStr);
    }

    public String getUnZippedString(String toUnzipStr){
        return this.myFiler.unZip(toUnzipStr);
    }

}
