package Profile;

import java.time.LocalDateTime;
import java.util.List;

public interface Registery {
    LocalDateTime getLastUpdate();
    List<FileData> getFiles();
}
