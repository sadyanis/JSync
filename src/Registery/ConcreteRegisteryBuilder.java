package Registery;

import java.sql.Date;

class ConcreteRegisteryBuilder implements RegisteryBuilder {
    private Registery registery;

    public ConcreteRegisteryBuilder() {
        this.registery = new Registery();
    }

    @Override
    public void addFile(String path, Date lastModified) {
        File file = new File(path, lastModified);
        registery.addFile(file);
    }

    @Override
    public Registery build() {
        return registery;
    }
}