package Sync;

import Profile.FileComposant;

import java.util.List;

public class StdFileComparator implements FileComparator {


    @Override
    public List<SyncAction> compare(List<FileComposant> DossierA, List<FileComposant> DossierB, List<FileComposant> Register) {
        return List.of();
    }
}
