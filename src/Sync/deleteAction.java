package Sync;

import java.nio.file.Path;
import java.util.Date;

public class deleteAction implements SyncAction{
    private String destination;
    private Date lastModified;

    public deleteAction(String path, Date lastModified) {


    }

    @Override
    public String getDestinationPath() {
        return this.destination;
    }

    @Override
    public Date getLastModified() {
        return this.lastModified;
    }

    @Override
    public void accept(SyncActionVisitor visitor) {
        visitor.visitDeleteAction(this);
    }
}
