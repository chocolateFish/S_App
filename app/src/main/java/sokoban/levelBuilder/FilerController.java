package sokoban.levelBuilder;

import sokoban.levelBuilder.model.FileHandler;

public class FilerController implements IFileHandlerCallback {
    FileHandler myModel;

    public FilerController(){
        this.myModel = new FileHandler();
    }

    public String getZippedString(String toZipStr){
        return this.myModel.zip(toZipStr);
    }

    public String getUnZippedString(String toUnzipStr){
        return this.myModel.unZip(toUnzipStr);
    }

}
