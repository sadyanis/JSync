package Sync;

import Enums.Direction;

import java.nio.file.Path;
import java.util.Date;
/**
 * Interface représentant une action de synchronisation(Create , Delete , Copy).
 * accepter un visiteur pour exécuter l'action.
 */
public  interface SyncAction {
    public String getPath() ;
    public abstract void accept(SyncActionVisitor visitor);
    public Direction getDirection();
}
