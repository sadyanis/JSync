package Registery;

import java.util.ArrayList;
import java.util.List;

public class Registery {
    private List<FileComposant> files;

    public Registery() {
        this.files = new ArrayList<>();
    }

    public void addFile(FileComposant file) {
        files.add(file);
    }

    public List<FileComposant> getFiles() {
        return files;
    }
}
