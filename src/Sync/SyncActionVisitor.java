package Sync;

public interface SyncActionVisitor {
    void visitCopyAction(copyAction copyAction);
    void visitDeleteAction(deleteAction deleteAction);
}
