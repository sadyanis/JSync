package File;
import java.util.ArrayList;
import java.util.List;

public class Dossier implements FileComponent {
    private String nom;
    private List<FileComponent> enfants = new ArrayList<>();

    public Dossier(String nom) {
        this.nom = nom;
    }

    public void ajouter(FileComponent enfant) {
        enfants.add(enfant);
    }

    public void retirer(FileComponent enfant) {
        enfants.remove(enfant);
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public long getDerniereModification() {
        // on pourrait calculer la date la plus récente des enfants
        long max = 0;
        for (FileComponent enfant : enfants) {
            max = Math.max(max, enfant.getDerniereModification());
        }
        return max;
    }

    @Override
    public void afficher() {
        System.out.println("Dossier : " + nom);
        for (FileComponent enfant : enfants) {
            enfant.afficher();
        }
    }
}

