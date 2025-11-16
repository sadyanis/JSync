package FileHandler;

import java.io.IOException;
import java.util.List;

import File.FileComposant;

public interface FileHandler {
    List<FileComposant> getFiles(String path);
    void copyFile(String source, String destination) throws IOException;
    void deleteFile(String path);

}
