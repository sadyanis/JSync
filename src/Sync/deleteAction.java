package Sync;

import java.nio.file.Path;
import java.util.Date;

public class deleteAction implements SyncAction{
    private String destination;

    public deleteAction(String path) {


    }

    @Override
    public String getDestinationPath() {
        return this.destination;
    }



    @Override
    public void accept(SyncActionVisitor visitor) {
        visitor.visitDeleteAction(this, new LocalFileHandler());
    }
}
