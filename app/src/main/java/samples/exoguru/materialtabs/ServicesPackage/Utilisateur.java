package samples.exoguru.materialtabs.ServicesPackage;

/**
 * Created by Milada on 27/05/2015.
 */
public class Utilisateur {

    private int utilisateur_cle;
    private String nom_utilisateur;

    public Utilisateur (int utilisateur_cle, String nom_utilisateur) {
        this.utilisateur_cle = utilisateur_cle;
        this.nom_utilisateur = nom_utilisateur;
    }

    public Utilisateur () {
    }

    public int getUtilisateur_cle () {
        return utilisateur_cle;
    }

    public String getNom_utilisateur () {
        return nom_utilisateur;
    }

    public void setUtilisateur_cle (int utilisateur_cle) {
        this.utilisateur_cle = utilisateur_cle;
    }

    public void setNom_utilisateur (String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }
}
