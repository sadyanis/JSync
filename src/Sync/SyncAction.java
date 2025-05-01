package Sync;

import java.nio.file.Path;
import java.util.Date;

public  interface SyncAction {



    public String getDestinationPath() ;
    public Date getLastModified();


    public abstract void accept(SyncActionVisitor visitor);
}
