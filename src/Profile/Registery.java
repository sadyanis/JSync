package Profile;

import java.time.LocalDateTime;
import java.util.List;

import File.FileComposant;

public interface Registery {
    LocalDateTime getLastUpdate();
    List<FileComposant> getRegistryFiles();
}
