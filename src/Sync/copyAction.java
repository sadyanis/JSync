package Sync;


import Enums.Direction;

import java.util.Date;

public class copyAction implements SyncAction {
    private String relativePath;
    private Direction direction;
    private Date lastModified;
    public copyAction(String relativePath , Direction direction , Date lastModified ) {
        this.relativePath = relativePath;
        this.direction = direction;
        this.lastModified = lastModified;
    }

    @Override
    public String getPath() {
        return this.relativePath;
    }


    public Date getLastModified() {
        return this.lastModified;
    }
    public Direction getDirection() {
        return this.direction;
    }


    @Override
    public void accept(SyncActionVisitor visitor ) {

        visitor.visitCopyAction(this);
    }


    }

