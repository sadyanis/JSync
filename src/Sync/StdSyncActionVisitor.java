package Sync;


import Enums.Direction;
import FileHandler.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StdSyncActionVisitor implements SyncActionVisitor {
    private  String rootA;
    private  String rootB;
    private FileHandler fileHandler;

    public StdSyncActionVisitor(String rootA, String rootB, FileHandler fileHandler) {
        this.rootA = rootA;
        this.rootB = rootB;
        this.fileHandler = fileHandler;
    }
    @Override
    public void visitCopyAction(copyAction action) {
        String srcRoot = action.getDirection() == Direction.A_TO_B ? rootA : rootB;
        String dstRoot = action.getDirection() == Direction.A_TO_B ? rootB : rootA;
        String srcPath = Paths.get(srcRoot).resolve(action.getPath()).toString();
        String dstPath = Paths.get(dstRoot).resolve(action.getPath()).toString();
        try {
            Files.createDirectories(Paths.get(dstRoot).getParent());
            fileHandler.copyFile(srcPath,dstPath);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void visitDeleteAction(deleteAction action) {
        if(action.getDirection() == Direction.ONLY_A){
            String pathA = Paths.get(rootA).resolve(action.getPath()).toString();
            fileHandler.deleteFile(pathA);
        }
        if (action.getDirection() == Direction.ONLY_B) {
            String pathB = Paths.get(rootB).resolve(action.getPath()).toString();
            fileHandler.deleteFile(pathB);
        }
    }
}
