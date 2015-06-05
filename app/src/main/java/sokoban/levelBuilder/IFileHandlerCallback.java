package sokoban.levelBuilder;

public interface IFileHandlerCallback {
    public String getZippedString(String toZipStr);
    public String getUnZippedString(String toUnzipStr);
}
