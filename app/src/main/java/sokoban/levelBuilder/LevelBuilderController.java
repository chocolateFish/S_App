package sokoban.levelBuilder;

import sokoban.filer.FileHandler;
import sokoban.filer.IFiler;
import sokoban.levelBuilder.model.ILevelMap;
import sokoban.levelBuilder.model.LevelMap;

public class LevelBuilderController implements IFileHandlerCallback, ILevelBuilderCallback {
    ILevelMap myModel;
    //TODO - find out if filer can be static
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

    public void buildLevel(String buildStr){
        this.myModel = new LevelMap();
        this.myModel.setString(buildStr);
    }

    public String levelToString(){
       return  this.myModel.getString();
    }
}
