package Sync;

import Profile.FileComposant;
import Profile.FileData;

import java.util.List;

public interface FileComparator {
    List<SyncAction> compare (List<FileComposant> DossierA , List<FileComposant> DossierB , List<FileComposant> Register);
}
