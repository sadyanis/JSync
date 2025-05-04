package Sync;
/**
 * Interface représentant un visiteur pour les actions de synchronisation.
 * Fournit des méthodes pour visiter et exécuter des actions spécifiques, comme copier ou supprimer des fichiers.
 */
public interface SyncActionVisitor {
    void visitCopyAction(copyAction copyAction);
    void visitDeleteAction(deleteAction deleteAction);
}
