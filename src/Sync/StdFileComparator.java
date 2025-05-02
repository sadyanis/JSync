package Sync;

import Profile.FileComposant;
import java.util.stream.Collectors;
import java.util.*;

public class StdFileComparator implements FileComparator {


    @Override
    public List<SyncAction> compare(List<FileComposant> folderA, List<FileComposant> folderB, List<FileComposant> registry) {

            Map<String, FileComposant> mapA = toMap(folderA);
            Map<String, FileComposant> mapB = toMap(folderB);
            Map<String, FileComposant> mapR = toMap(registry);

            Set<String> allPaths = new HashSet<>();
            allPaths.addAll(mapA.keySet());
            allPaths.addAll(mapB.keySet());
            allPaths.addAll(mapR.keySet());

            List<SyncAction> actions = new ArrayList<>();

            for (String path : allPaths) {
                FileComposant fa = mapA.get(path);
                FileComposant fb = mapB.get(path);
                FileComposant fr = mapR.get(path);

                if (fa != null && fb == null) {
                    // Fichier dans A mais pas dans B
                    if (fr == null || fa.getLastModified().after(fr.getLastModified())) {
                        actions.add(new copyAction(fa.getPath(), path,fa.getLastModified() )); // de A vers B
                    }
                } else if (fa == null && fb != null) {
                    // Fichier dans B mais pas dans A
                    if (fr == null || fb.getLastModified().after(fr.getLastModified())) {
                        actions.add(new copyAction(fb.getPath(), path,fb.getLastModified())); // de B vers A
                    }
                } else if (fa != null && fb != null) {
                    // Les deux existent
                    Date da = fa.getLastModified();
                    Date db = fb.getLastModified();
                    Date dr = fr != null ? fr.getLastModified() : new Date(0);

                    if (da.after(db) && da.after(dr)) {
                        actions.add(new copyAction(fa.getPath(), path,fa.getLastModified())); // A vers B
                    } else if (db.after(da) && db.after(dr)) {
                        actions.add(new copyAction(fb.getPath(), path,fb.getLastModified())); // B vers A
                    }
                } else if (fr != null && fa == null && fb == null) {
                    // Fichier supprimé des deux côtés
                    actions.add(new deleteAction(path)); // Suppression globale
                }
            }

            return actions;
        }

        private Map<String, FileComposant> toMap(List<FileComposant> files) {
            return files.stream().collect(Collectors.toMap(FileComposant::getPath, f -> f));
        }
    }

