package Sync;

import Profile.FileComposant;

import java.util.List;

public class RemoteFileHandler implements FileHandler {

    @Override
    public List<FileComposant> getFiles(String path) {
        return List.of();
    }

    @Override
    public void copyFile(String source, String destination) {

    }

    @Override
    public void deleteFile(String path) {

    }
}
