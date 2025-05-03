package Registery;

import java.sql.Date;

interface RegisteryBuilder {
    void addFile(String path, Date lastModified);
    Registery build();
}