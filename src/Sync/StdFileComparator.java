package Sync;

import Profile.FileComposant;

import java.util.stream.Collectors;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import Registery.*;

public class StdFileComparator implements FileComparator {


    @Override
//    public List<SyncAction> compare(List<FileComposant> folderA, List<FileComposant> folderB, List<FileComposant> registry) {
//
//            Map<String, FileComposant> mapA = toMap(folderA);
//            Map<String, FileComposant> mapB = toMap(folderB);
//            Map<String, FileComposant> mapR = toMap(registry);
//
//            Set<String> allPaths = new HashSet<>();
//            allPaths.addAll(mapA.keySet());
//            allPaths.addAll(mapB.keySet());
//            allPaths.addAll(mapR.keySet());
//
//            List<SyncAction> actions = new ArrayList<>();
//
//            for (String path : allPaths) {
//                FileComposant fa = mapA.get(path);
//                FileComposant fb = mapB.get(path);
//                FileComposant fr = mapR.get(path);
//
//                if (fa != null && fb == null) {
//                    // Fichier dans A mais pas dans B
//                    if (fr == null || fa.getLastModified().after(fr.getLastModified())) {
//                        actions.add(new copyAction(fa.getPath(), path,fa.getLastModified() )); // de A vers B
//                    }
//                } else if (fa == null && fb != null) {
//                    // Fichier dans B mais pas dans A
//                    if (fr == null || fb.getLastModified().after(fr.getLastModified())) {
//                        actions.add(new copyAction(fb.getPath(), path,fb.getLastModified())); // de B vers A
//                    }
//                } else if (fa != null && fb != null) {
//                    // Les deux existent
//                    Date da = fa.getLastModified();
//                    Date db = fb.getLastModified();
//                    Date dr = fr != null ? fr.getLastModified() : new Date(0);
//
//                    if (da.after(db) && da.after(dr)) {
//                        actions.add(new copyAction(fa.getPath(), path,fa.getLastModified())); // A vers B
//                    } else if (db.after(da) && db.after(dr)) {
//                        actions.add(new copyAction(fb.getPath(), path,fb.getLastModified())); // B vers A
//                    }
//                } else if (fr != null && fa == null && fb == null) {
//                    // Fichier supprimé des deux côtés
//                    actions.add(new deleteAction(path)); // Suppression globale
//                }
//            }
//
//            return actions;
//        }
//
//        private Map<String, FileComposant> toMap(List<FileComposant> files) {
//            return files.stream().collect(Collectors.toMap(FileComposant::getPath, f -> f));
//        }
    public List<SyncAction> compare(List<FileComposant> folderA, List<FileComposant> folderB, List<FileComposant> registery){
        List<SyncAction> actions = new ArrayList<>();
        // Recuperer tous les chemins uniques dans A et B et registry
        List<String> allPaths = getAllPaths(folderA, folderB, registery);
        for (String path : allPaths ){
            FileComposant fileA = findFileByPath(path, folderA);
            FileComposant fileB = findFileByPath(path, folderB);
            FileComposant registryFile = findFileByPath(path, registery);

            // si c'est dossier comparer recursivement ses enfants
            if(fileA != null && fileA.isDirectory() || fileB != null && fileB.isDirectory()){
                List<FileComposant> childrenA = fileA != null ? fileA.getChildren() : new ArrayList<>();
                List<FileComposant> childrenB = fileB != null ? fileB.getChildren() : new ArrayList<>();
                List<FileComposant> childrenRegistry = registryFile != null ? registryFile.getChildren() : new ArrayList<>();

                actions.addAll(compare(childrenA, childrenB, childrenRegistry));
            } else {
                // si c'est un fichier comparer les dates
                String stateA = getFileState(fileA, registryFile);
                String stateB = getFileState(fileB, registryFile);
                actions.addAll(generateActionsForFile(path, stateA, stateB, fileA, fileB));

            }
        }
        return actions;
    }
    private List<String> getAllPaths(List<FileComposant> folderA, List<FileComposant> folderB, List<FileComposant> registry) {
        List<String> paths = new ArrayList<>();
        paths.addAll(folderA.stream().map(FileComposant::getPath).collect(Collectors.toList()));
        paths.addAll(folderB.stream().map(FileComposant::getPath).collect(Collectors.toList()));
        paths.addAll(registry.stream().map(FileComposant::getPath).collect(Collectors.toList()));
        return paths.stream().distinct().collect(Collectors.toList());
    }
    // trouve un fichier par son chemin
    private FileComposant findFileByPath(String path, List<FileComposant> files) {
        return files.stream()
                .filter(f -> f.getPath().equals(path))
                .findFirst()
                .orElse(null);
    }
    // determine l'etat d'un fichier par rapport au registre
    private String getFileState(FileComposant file, FileComposant registryFile) {
        if (file == null) {
            return "⊥";  // Absent
        }
        if (registryFile == null) {
            return "+";  // Nouveau
        }
        Date lastModified = file.getLastModified();
        Date registryDate = registryFile.getLastModified();
        if (lastModified.after(registryDate)) {
            return "+";  // Modifié
        }
        return "=";  // Inchangé
    }
    // genere les actions de synchronisation pour un fichier selon les etats dans A et B
    private List<SyncAction> generateActionsForFile(String path, String stateA, String stateB, FileComposant fileA, FileComposant fileB) {
        List<SyncAction> actions = new ArrayList<>();

        if (stateA.equals("+") && stateB.equals("⊥")) {
            // Nouveau fichier dans A, copier vers B
            actions.add(new copyAction(path, path, fileA.getLastModified()));
        } else if (stateA.equals("⊥") && stateB.equals("+")) {
            // Nouveau fichier dans B, copier vers A
            actions.add(new copyAction(path, path, fileB.getLastModified()));
        } else if (stateA.equals("=") && stateB.equals("=")) {
            // Pas de changement, aucune action
        } else if (stateA.equals("+") && stateB.equals("=")) {
            // Fichier modifié dans A, mettre à jour B
            actions.add(new copyAction(path, path, fileA.getLastModified()));
        } else if (stateA.equals("=") && stateB.equals("+")) {
            // Fichier modifié dans B, mettre à jour A
            actions.add(new copyAction(path, path, fileB.getLastModified()));
        } else if (stateA.equals("+") && stateB.equals("+")) {
            // Conflit : les deux fichiers ont été modifiés
            // Stratégie : copier le fichier le plus récent
            if (fileA.getLastModified().after(fileB.getLastModified())) {
                actions.add(new copyAction(path, path, fileA.getLastModified()));
            } else {
                actions.add(new copyAction(path, path, fileB.getLastModified()));
            }
        } else if (stateA.equals("=") && stateB.equals("⊥")) {
            // Fichier supprimé dans B, supprimer dans A
            actions.add(new deleteAction(path));
        } else if (stateA.equals("⊥") && stateB.equals("=")) {
            // Fichier supprimé dans A, supprimer dans B
            actions.add(new deleteAction(path));
        }

        return actions;
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

