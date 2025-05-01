# JSync

![Texte alternatif](/UML_global%20.png)
## Exemple interface SyncAction
```java
interface SyncAction {
    void accept(SyncActionVisitor visitor);
    FileData getSource();
    FileData getDestination();
    IFileHandler getSourceHandler(); // Nouveau
    IFileHandler getDestinationHandler(); // Nouveau
}
```