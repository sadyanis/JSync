import Profile.*;
import Sync.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ProfilePersistence.ProfilePersistence;
import ProfilePersistence.XmlProfilePersistence;
public class Main {
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, SAXException {
	    // Tester la creation d'un profile
	        // Demander au user le nom du profile
	        
	        System.out.println("Entrez le nom du profile :");
	        Scanner scanner = new Scanner(System.in);
	        String profileName = scanner.nextLine();
	        // Demander au user le chemin du dossier A
	        System.out.println("Entrez le chemin du dossier A :");
	        String pathA = scanner.nextLine();
	        // Demander au user le chemin du dossier B
	        System.out.println("Entrez le chemin du dossier B :");
	        String pathB = scanner.nextLine();
	        Profile profile = new StdProfile(pathA, pathB, profileName,true , true);
	        ProfilePersistence persistence = new XmlProfilePersistence();
	        // Enregistrer le profile
	        persistence.initProfileWithRegistery(profile);
	        System.out.println("Profile enregistré avec succès !");
	    }
}