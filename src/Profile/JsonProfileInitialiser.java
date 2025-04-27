package Profile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonProfileInitialiser implements ProfilePersistance {

    @Override
    public void initProfileWithRegistery(Profile profile) throws IOException {
        Path filePath = Path.of(profile.getName() + ".sync");
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write("{\n");

            // Données du profil
            writer.write("  \"name\": \"" + profile.getName() + "\",\n");
            writer.write("  \"folderA\": \"" + profile.getPathFolderA() + "\",\n");
            writer.write("  \"folderB\": \"" + profile.getPathFolderB() + "\",\n");

            // Données du registre (vide pour l'instant)
            writer.write("  \"registry\": {}\n");

            writer.write("}\n");
        }
    }

    @Override
    public void updateProfileWithRegistery(Profile profile) throws IOException {
        //
    }
}
