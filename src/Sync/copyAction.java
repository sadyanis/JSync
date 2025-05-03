package Sync;

import java.util.Date;

public class copyAction implements SyncAction {
    private String source;
    private String destination;
    private Date lastModified;
    public copyAction(String source , String target , Date lastModified ) {
        this.destination = target;
        this.source = source;
        this.lastModified = lastModified;
    }

    @Override
    public String getDestinationPath() {
        return this.destination;
    }


    public Date getLastModified() {
        return this.lastModified;
    }
    public String getSourcePath() {
        return this.source;
    }

    @Override
    public void accept(SyncActionVisitor visitor ) {
        visitor.visitCopyAction(this,new LocalFileHandler());
    }


    }

