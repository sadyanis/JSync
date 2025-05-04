package File;

import java.util.Date;
import java.util.List;

public interface FileComposant {
    String getPath();
    Date getLastModified ();
    boolean isDirectory();
    List<FileComposant> getChildren();

}
