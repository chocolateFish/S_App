package sokoban.levelBuilder;

import sokoban.IFileHandlerCallback;
import sokoban.filer.FileHandler;
import sokoban.filer.IFiler;
import sokoban.levelBuilder.model.ILevelMap;
import sokoban.levelBuilder.model.LevelMap;

public class LevelBuilderController implements  ILevelBuilderCallback {
    ILevelMap myModel;
    public LevelBuilderController(){
     super();
    }

    public void buildLevel(String buildStr){
        this.myModel = new LevelMap();
        this.myModel.setString(buildStr);
    }

    public String levelToString(){
       return  this.myModel.getString();
    }
}
