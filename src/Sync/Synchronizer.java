package Sync;
import Profile.Profile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

// Orchestre la synchronisation d'un profile
public interface Synchronizer {
    void synchronize(String profilePath)throws IOException, ParserConfigurationException, TransformerException, SAXException;
}
