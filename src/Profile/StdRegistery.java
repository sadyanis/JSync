package Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import File.FileComposant;
/**
 * Classe représentant un registre de synchronisation standard.
 * Implémente l'interface {@link Registery}
 */
public class StdRegistery implements Registery {
    private LocalDateTime lastUpdate;
    private List<FileComposant> files;

    public StdRegistery() {
        files = new ArrayList<>();
    }
    public StdRegistery(List<FileComposant> files , LocalDateTime lastUpdate) {
        this.files = files;
        this.lastUpdate = lastUpdate;
    }
    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public List<FileComposant> getRegistryFiles() {
        return files;
    }
}
