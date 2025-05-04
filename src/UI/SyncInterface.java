package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Profile.Profile;
import Registery.Registery;
import Sync.Synchronizer;
import Profile.FileComposant;

public class SyncInterface extends JFrame {

    public SyncInterface(Profile profile, Registery registery, Synchronizer synchronizer, JFrame previousFrame) {
        setTitle("Synchronisation - " + profile.getName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel gauche avec bouton de synchronisation
        JPanel leftPanel = new JPanel(new BorderLayout());
        JButton syncButton = new JButton("Lancer la synchronisation");
        
        JButton backButton = new JButton("Retour");
        leftPanel.add(backButton, BorderLayout.SOUTH);
        
        backButton.addActionListener(e -> {
            previousFrame.setVisible(true); // Affiche l'interface précédente
            dispose(); // Ferme l'interface actuelle
        });
        
        leftPanel.add(syncButton, BorderLayout.NORTH);

        // ➕ Action quand on clique sur le bouton
        syncButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    //synchronizer.synchronize("/home/mysthic/Desktop/JSync/JSync/profiles/mouloud.sync");
                    synchronizer.synchronize(profile.getName());
                    JOptionPane.showMessageDialog(null, "Synchronisation terminée !");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Panel droit avec la liste des fichiers du registre
        DefaultListModel<String> fileListModel = new DefaultListModel<>();
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
