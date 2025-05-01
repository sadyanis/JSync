package Sync;

public interface SyncActionVisitor {
    void visitCopyAction(SyncAction copyAction);
    void visitDeleteAction(SyncAction syncAction);
}
