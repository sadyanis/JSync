package Sync;

import Profile.DirectoryComposite;
import Profile.FileComposant;
import Profile.FileData;

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

public class LocalFileHandler implements FileHandler {
    @Override
//    public List<FileComposant> getFiles(String path) {
//        // Parcourir le répertoire local et retourner la liste des fichiers
//        List<FileComposant> components = new ArrayList<>();
//        File root = new File(path);
//
//        if (!root.exists() || !root.isDirectory()) {
//            return components;
//        }
//
//        for (File file : root.listFiles()) {
//
//            if (file.isFile()) {
//                FileData fileData = new FileData(file.getAbsolutePath(), new Date(file.lastModified()));
//                components.add(fileData);
//            } else if (file.isDirectory()) {
//                // Récursivement construire ses enfants
//
//                List<FileComposant> children = getFiles(file.getPath());
//                DirectoryComposite dir = new DirectoryComposite(file.getPath(), children);
//                components.add(dir);
//            }
//        }
//
//        return components;
//    }
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
            // Copie récursive du dossier
            Files.walk(src).forEach(path -> {
                try {
                    Path targetPath = dest.resolve(src.relativize(path));
                    if (Files.isDirectory(path)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } else {
            // Copie d'un fichier simple
            if (dest.getParent() != null) {
                Files.createDirectories(dest.getParent()); // Crée les dossiers parents si besoin
            }
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
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
    }
}
