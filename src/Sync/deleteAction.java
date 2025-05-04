package Sync;

import Enums.Direction;

public class deleteAction implements SyncAction{
    private String relativePath;
    private Direction direction;


    public deleteAction(String path , Direction direction) {
        this.relativePath = path;
        this.direction = direction;


    }

    @Override
    public String getPath() {
        return this.relativePath;
    }

    @Override
    public void accept(SyncActionVisitor visitor) {

        visitor.visitDeleteAction(this);
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }


}
