package ProfilePersistence;

import org.xml.sax.SAXException;

import File.FileComposant;
import Profile.Profile;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

// class responsable d'enregistrer le profile et le registery dans un fichier
public interface ProfilePersistence {
    public void serializeRegistry(List<FileComposant> files, String path)throws IOException, TransformerException, ParserConfigurationException, SAXException;
    void initProfileWithRegistery(Profile profile ) throws IOException;
     void updateRegistery(List<FileComposant> files);
     Profile loadProfile(String path) throws IOException;

}
