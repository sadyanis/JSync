package Profile;

import java.util.Date;
import java.util.List;

public class DirectoryComposite implements FileComposant {
   private String path;
   private List<FileComposant> children;

   public DirectoryComposite(String path, List<FileComposant> children) {
       this.path = path;
       this.children = children;
   }
    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Date getLastModified() {
        return this.children.stream().map(FileComposant::getLastModified).max(Date::compareTo).get();
    }
}
