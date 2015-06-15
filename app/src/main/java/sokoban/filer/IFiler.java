package sokoban.filer;

import java.io.IOException;
/**
 * Code by Filer Model group
 * added abstract methods
 */

public interface IFiler {
    String zip (String map);
    String unZip(String zippedMap);

    String importMap (String filePath)throws IOException;
    void exportMap(String map, String fileName)throws IOException;

    String loadMap (String key);
    void saveMap(String map, String key);
    String[] loadAll();
    String getKeyAvailability(String Key);
    boolean containsData();
    void removeData(String key);
}

