package UI;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Sync.*;
import Profile.Profile;
import Profile.ProfilePersistance;
import Profile.XmlProfilePersistance;
import org.xml.sax.SAXException;

public class WelcomeController {
	
	private static final ProfilePersistance profilePersistance = new XmlProfilePersistance();
	private static final FileHandlerFactory fileHandlerFactory = new LocalFileHandlerFactory();
    private static final FileComparator comparator = new StdFileComparator();
	private static final StdSynchronizer synchronizer = new StdSynchronizer(fileHandlerFactory,profilePersistance,comparator);
	
    public static void main(String[] args) {

    	
        SwingUtilities.invokeLater(WelcomeController::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Bienvenue dans JSync");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titre
        JLabel titleLabel = new JLabel("Liste des profils disponibles :", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Liste des profils
        DefaultListModel<File> listModel = new DefaultListModel<>(); // Changer pour Stocker des objets File
        JList<File> profileList = new JList<>(listModel); // Changer pour afficher des objets File
        profileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        profileList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(profileList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Message en bas
        JLabel selectedProfileLabel = new JLabel("Sélectionnez un profil pour voir son nom.", SwingConstants.CENTER);
        selectedProfileLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        panel.add(selectedProfileLabel, BorderLayout.SOUTH);

        // Ajouter un listener de clic
        profileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                File selectedFile = profileList.getSelectedValue();
                if (selectedFile != null) {
                    String absolutePath = selectedFile.getAbsolutePath();
                    selectedProfileLabel.setText("Profil sélectionné : " + absolutePath);

                    try {
                        Profile profile = profilePersistance.loadProfile(absolutePath);
                        System.out.println(profile.getName() + " " + profile.getPathFolderA());
                        synchronizer.synchronize(absolutePath);
                        JOptionPane.showMessageDialog(frame, "Le profile de " + profile.getName()  + " a été chargé correctement !");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erreur lors du chargement du profil : " + ex.getMessage());
                    } catch (ParserConfigurationException ex) {
                        throw new RuntimeException(ex);
                    } catch (TransformerException ex) {
                        throw new RuntimeException(ex);
                    } catch (SAXException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Charger les profils
        File profilesDir = new File("./profiles");
        if (profilesDir.exists() && profilesDir.isDirectory()) {
            File[] files = profilesDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".sync"));
            if (files != null) {
                for (File file : files) {
                    listModel.addElement(file); // Ajouter l'objet File au lieu du nom
                }
            } else {
                listModel.addElement(new File("Aucun fichier .sync trouvé"));
            }
        } else {
            listModel.addElement(new File("Dossier ./profiles introuvable"));
        }

        // Finaliser
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
