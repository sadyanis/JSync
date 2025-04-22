package Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StdRegistery implements Registery {
    private LocalDateTime lastUpdate;
    private List<FileData> files;

    public StdRegistery() {
        files = new ArrayList<>();
    }
    public StdRegistery(List<FileData> files , LocalDateTime lastUpdate) {
        this.files = files;
        this.lastUpdate = lastUpdate;
    }
    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public List<FileData> getFiles() {
        return files;
    }
}
