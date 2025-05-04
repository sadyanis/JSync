package Sync;

import java.util.List;

import File.FileComposant;
import File.FileData;

public interface FileComparator {
    List<SyncAction> compare (List<FileComposant> DossierA , List<FileComposant> DossierB , List<FileComposant> Register);
}
