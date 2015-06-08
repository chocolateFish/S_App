package sokoban.levelBuilder;

import sokoban.levelBuilder.model.ILevelMap;

/**
 * Created by fab66 on 8/06/2015.
 */
public interface ILevelBuilderCallback {
    void buildLevel(String buildStr);
    String levelToString();

}
