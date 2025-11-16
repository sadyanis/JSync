package FileHandler;

import java.util.List;

import File.FileComposant;
/*
* Implementation de FileHandler pour gérer les fichiers distants.
* */
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
