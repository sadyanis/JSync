package ProfilePersistence;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;


@XmlRootElement(name = "syncProfile")
public class XmlProfile {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "folderA")
    private String folderA;

    @XmlElement(name = "folderB")
    private String folderB;

    // Constructeurs, getters...

    public String getName() {
        return name;
    }

    public String getFolderA() {
        return folderA;
    }

    public String getFolderB() {
        return folderB;
    }
}

