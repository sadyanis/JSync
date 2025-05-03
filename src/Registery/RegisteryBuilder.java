package Registery;

import java.util.Date;

public interface RegisteryBuilder {
    void addFile(String path, Date lastModified);
    Registery build();
}