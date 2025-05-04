package UI;

import Profile.*;
import Registery.*;
import Sync.*;
import Registery.Registery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import Profile.*;
import Sync.*;
import Registery.Registery;
import Registery.RegisteryBuilder;
import Registery.RegisteryParser;
import Profile.XmlProfilePersistence;
public class MainUI {
	
	private static final ProfilePersistence profilePersistance = new XmlProfilePersistence(); // Ensure XmlProfilePersistance implements ProfilePersistance
	private static final FileHandlerFactory fileHandlerFactory = new LocalFileHandlerFactory();
    private static final FileComparator comparator = new StdFileComparator();
	private static final StdSynchronizer synchronizer = new StdSynchronizer(fileHandlerFactory, profilePersistance, comparator );
	
    public static void main(String[] args) {

    	
        SwingUtilities.invokeLater(MainUI::createAndShowGUI);
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
//
//                    try {
//                        Profile profile = profilePersistance.loadProfile(absolutePath);
//                        System.out.println(profile.getName() + " " + profile.getPathFolderA());
//                        synchronizer.synchronize(profile);
//                        JOptionPane.showMessageDialog(frame, "Le profile de " + profile.getName()  + " a été chargé correctement !");
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                        JOptionPane.showMessageDialog(frame, "Erreur lors du chargement du profil : " + ex.getMessage());
//                    }
                	try {
                        Profile profile = profilePersistance.loadProfile(absolutePath);

                        // Charger le registre
                        String registryPath = "profiles/" + profile.getName() + ".sync";
                        //InputStream is = getClass().getClassLoader().getResourceAsStream(registryPath);
                        InputStream is = new FileInputStream(registryPath);
                        if (is == null) {
                            throw new RuntimeException("Registre introuvable : " + registryPath);
                        }
                        
                        String data = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                        RegisteryBuilder builder = new ConcreteRegisteryBuilder();
                        RegisteryParser parser = new XMLRegisteryParser();
                        RegisteryLoader loader = new RegisteryLoader(parser, builder);
                        Registery registery = loader.load(data);

                        // Rediriger vers la nouvelle interface
                        StdSynchronizer synchronizer = new StdSynchronizer(new LocalFileHandlerFactory(), new XmlProfilePersistence(), new StdFileComparator());
                        new SyncInterface(profile, registery, synchronizer);// passer synchronizer l'objet aussi a l'autre ui

                        frame.dispose(); // Ferme l’ancienne fenêtre

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erreur : " + ex.getMessage());
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
