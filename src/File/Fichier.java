package File;

public class Fichier implements FileComponent {
    private String nom;
    private long derniereModification;

    public Fichier(String nom, long derniereModification) {
        this.nom = nom;
        this.derniereModification = derniereModification;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public long getDerniereModification() {
        return derniereModification;
    }

    @Override
    public void afficher() {
        System.out.println("Fichier : " + nom);
    }
}

