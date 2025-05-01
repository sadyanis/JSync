package Profile;


import java.util.Date;

public class FileData implements FileComposant  {

    private String path;
    private Date lastModified;

    public FileData(String path, Date lastModified) {
        this.path = path;
        this.lastModified = lastModified;
    }
    public String getPath() {
        return path;
    }


    public Date getLastModified() {
        return lastModified;
    }

}
