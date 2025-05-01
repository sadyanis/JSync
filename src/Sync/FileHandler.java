package Sync;

import Profile.FileComposant;

import java.util.List;

public interface FileHandler {
    List<FileComposant> getFiles(String path);
    void copyFile(String source, String destination);
    void deleteFile(String path);

}
