package Registery;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import File.FileComposant;

public class MainTest {
    public static void main(String[] args) {
        getRegistery("registery");
    }

    public static Registery getRegistery(String name) {
        try {
            String resourcePath = "Registery/" + name + ".sync"; // remplacer Registery par le chemin qui v conteniur les fichies

            InputStream is = MainTest.class.getClassLoader().getResourceAsStream(resourcePath);
            if (is == null) {
                throw new RuntimeException("Fichier introuvable dans le classpath : " + resourcePath);
            }

            String data = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            RegisteryBuilder builder = new ConcreteRegisteryBuilder();
            RegisteryParser parser = new XMLRegisteryParser();
            RegisteryLoader loader = new RegisteryLoader(parser, builder);

            Registery registery = loader.load(data);

            System.out.println("Fichiers trouvés : " + registery.getFiles().size());
            for (FileComposant file : registery.getFiles()) {
                System.out.println("Path: " + file.getPath() + ", Last Modified: " + file.getLastModified());
            }

            return registery;

        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture ou du parsing du fichier : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
