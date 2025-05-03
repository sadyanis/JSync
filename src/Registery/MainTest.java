package Registery;

public class MainTest {
    public static void main(String[] args) {
        try {
            // Chemin vers le fichier XML (ajuste selon ton environnement)
            String filePath = "registry.xml";

            // Lire le contenu du fichier XML sous forme de chaîne
            String xmlData = new String(Files.readAllBytes(Paths.get(filePath)));

            // Créer les composants
            RegisteryBuilder builder = new ConcreteRegisteryBuilder();
            RegisteryParser parser = new XMLRegisteryParser();
            RegisteryLoader loader = new RegisteryLoader(parser, builder);

            // Charger le Registery depuis le XML
            Registery registery = loader.load(xmlData);

            // Afficher les fichiers
            for (FileComposant file : registery.getFiles()) {
                System.out.println("Path: " + file.getPath() + ", Last Modified: " + file.getLastModified());
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture ou du parsing du fichier XML : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
