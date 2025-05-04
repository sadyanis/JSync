package Profile;

import java.time.LocalDateTime;
import java.util.List;

import File.FileComposant;
/**
 * Interface représentant un registre de synchronisation.

 */
public interface Registery {
    LocalDateTime getLastUpdate();
    List<FileComposant> getRegistryFiles();
}
