package FileHandler;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import File.DirectoryComposite;
import File.FileComposant;
import File.FileData;

public class LocalFileHandler implements FileHandler {
    @Override

    public List<FileComposant> getFiles(String basePath) {
        return getFilesRecursive(new File(basePath), basePath);
    }

    private List<FileComposant> getFilesRecursive(File current, String basePath) {
        List<FileComposant> components = new ArrayList<>();

        if (!current.exists() || !current.isDirectory()) {
            return components;
        }

        for (File file : current.listFiles()) {
            // Obtenir le chemin relatif
            String relativePath = basePath.endsWith(File.separator)
                    ? file.getAbsolutePath().substring(basePath.length())
                    : file.getAbsolutePath().substring(basePath.length() + 1);

            if (file.isFile()) {
                FileData fileData = new FileData(relativePath, new Date(file.lastModified()));
                components.add(fileData);
            } else if (file.isDirectory()) {
                List<FileComposant> children = getFilesRecursive(file, basePath);
                DirectoryComposite dir = new DirectoryComposite(relativePath, children);
                components.add(dir);
            }
        }
        return components;
    }


    @Override
    public void copyFile(String source, String destination) throws IOException {
        Path src = Paths.get(source);
        Path dest = Paths.get(destination);

        if (Files.isDirectory(src)) {
            // copie recursive du dossier
            Files.walk(src).forEach(path -> {
                try {
                    Path targetPath = dest.resolve(src.relativize(path));
                    if (Files.isDirectory(path)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                        // afficher un message de succès
                        System.out.println("Fichier copié : " + path + " vers " + targetPath);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } else {
            // copie d'un fichier simple
            if (dest.getParent() != null) {
                Files.createDirectories(dest.getParent()); // Crée les dossiers parents si besoin

                System.out.println("Dossier parent créé : " + dest.getParent());
            }
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            // afficher un message de succès
            System.out.println("Fichier copié : " + source + " vers " + destination);
        }
    }

    @Override
    public void deleteFile(String path) {
        Path target = Paths.get(path);
        try {
            Files.deleteIfExists(target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // afficher un message de succès
        System.out.println("Fichier supprimé : " + path);
    }
}