package Sync;

import Enums.Direction;

import java.nio.file.Path;
import java.util.Date;

public  interface SyncAction {
    public String getPath() ;
    public abstract void accept(SyncActionVisitor visitor);
    public Direction getDirection();
}
