package Registery;

import java.util.Date;

import Profile.FileData;
public class ConcreteRegisteryBuilder implements RegisteryBuilder {
    private Registery registery;

    public ConcreteRegisteryBuilder() {
        this.registery = new Registery();
    }

    @Override
    public void addFile(String path, Date lastModified) {
        FileData file = new FileData(path, lastModified);
        registery.addFile(file);
    }

    @Override
    public Registery build() {
        return registery;
    }
}