package sokoban.filer;

import java.io.IOException;

public interface IFiler {
    //static String zip (String map)
   // String unZip(String zippedMap);
    String importMap (String filePath) throws IOException;
    void exportMap(String map, String fileName) throws IOException;


}
