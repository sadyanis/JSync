package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Profile.Profile;
import Registery.Registery;
import Sync.Synchronizer;
import Profile.FileComposant;
public class SyncInterface extends JFrame {
        public SyncInterface(Profile profile, Registery registery, Synchronizer synchronizer) {
            setTitle("Synchronisation - " + profile.getName());
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Panel gauche avec bouton de synchronisation
            JPanel leftPanel = new JPanel(new BorderLayout());
            JButton syncButton = new JButton("Lancer la synchronisation");
            leftPanel.add(syncButton, BorderLayout.NORTH);

            // Panel droit avec la liste des fichiers du registre
            DefaultListModel<String> fileListModel = new DefaultListModel<>();
            //List<String> filePaths = registery.getPaths(); // méthode à définir selon ta structure

            for (FileComposant file : registery.getFiles()) {
                fileListModel.addElement(file.getPath());
            }

            JList<String> fileList = new JList<>(fileListModel);
            JScrollPane scrollPane = new JScrollPane(fileList);
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.add(new JLabel("Fichiers du registre :"), BorderLayout.NORTH);
            rightPanel.add(scrollPane, BorderLayout.CENTER);

            // JSplitPane : gauche (bouton), droite (liste)
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
            splitPane.setDividerLocation(250);

            add(splitPane);
            setVisible(true);
        }

}
