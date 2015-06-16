package samples.exoguru.materialtabs.ServicesPackage;

/**
 * Created by Milada on 27/05/2015.
 */
public class Commentaire {

    private int id_commentaire;
    private String commentaire_text;
    private int id_contenu;
    private int id_utilisateur;

    public Commentaire (int id_commentaire, String commentaire_text, int id_contenu, int id_utilisateur) {
        this.id_commentaire = id_commentaire;
        this.commentaire_text = commentaire_text;
        this.id_contenu = id_contenu;
        this.id_utilisateur = id_utilisateur;
    }

    public Commentaire () {
    }

    public int getId_commentaire () {
        return id_commentaire;
    }

    public String getCommentaire_text () {
        return commentaire_text;
    }

    public int getId_contenu () {
        return id_contenu;
    }

    public int getId_utilisateur () {
        return id_utilisateur;
    }

    public void setId_commentaire (int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setCommentaire_text (String commentaire_text) {
        this.commentaire_text = commentaire_text;
    }

    public void setId_contenu (int id_contenu) {
        this.id_contenu = id_contenu;
    }

    public void setId_utilisateur (int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
}
