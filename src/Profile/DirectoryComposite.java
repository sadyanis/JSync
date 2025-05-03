package Profile;

import java.util.Collections;
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
        return children.stream()
                .map(FileComposant::getLastModified)
                .max(Date::compareTo)
                .orElse(new Date(0));
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
    @Override
    public List<FileComposant> getChildren() {
         return Collections.unmodifiableList(children);
    }
    public void setChildren(List<FileComposant> children) {
        this.children = children;
    }
    public void addChild(FileComposant child) {
        this.children.add(child);
    }
    public void removeChild(FileComposant child) {
        this.children.remove(child);
    }
}
