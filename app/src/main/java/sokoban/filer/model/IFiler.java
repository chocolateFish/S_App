package sokoban.filer.model;

import java.io.IOException;

public interface IFiler {
    public String zip (String map);
    public String unZip(String zippedMap);
    public String importMap (String filePath) throws IOException;
    public void exportMap(String map, String fileName) throws IOException;
}
