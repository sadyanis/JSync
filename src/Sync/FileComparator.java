package Sync;

import Profile.FileData;

import java.util.List;

public interface FileComparator {
    List<SyncAction> compare (List<FileData> DossierA , List<FileData> DossierB , List<FileData> Register);
}
