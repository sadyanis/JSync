package Sync;

public interface SyncActionVisitor {
    void visitCopyAction(SyncAction copyAction, FileHandler fileHandler);
    void visitDeleteAction(SyncAction syncAction, FileHandler fileHandler);
}
