package sokoban.filer;

public interface IFileHandlerCallback {
    public String getZippedString(String toZipStr);
    public String getUnZippedString(String toUnzipStr);
}
