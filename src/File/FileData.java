package File;


import java.util.Collections;
import java.util.Date;
import java.util.List;
/*
    * Représente un fichier.
 */
public class FileData implements FileComposant  {

    private String path;
    private Date lastModified;

    public FileData(String path, Date lastModified) {
        this.path = path;
        this.lastModified = lastModified;
    }
    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
    @Override
    public List<FileComposant> getChildren() {
        return Collections.emptyList();
    }


}
