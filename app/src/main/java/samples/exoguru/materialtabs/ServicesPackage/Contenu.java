package samples.exoguru.materialtabs.ServicesPackage;

import java.io.File;
import java.util.Date;

/**
 * Created by Milada on 27/05/2015.
 */
public class Contenu {

    private int id_contenu;
    private Date date_publication;
    private Date date_modification;
    private String text;
    private int id_utilisateur;
    private String type;
    private byte[] fichier;

    public Contenu (int id_contenu, Date date_publication, Date date_modification, String text, int id_utilisateur, String type, byte[] fichier) {
        this.id_contenu = id_contenu;
        this.date_publication = date_publication;
        this.date_modification = date_modification;
        this.text = text;
        this.id_utilisateur = id_utilisateur;
        this.type = type;
        this.fichier = fichier;
    }

    public Contenu () {
    }

    public int getId_contenu () {
        return id_contenu;
    }

    public Date getDate_publication () {
        return date_publication;
    }

    public Date getDate_modification () {
        return date_modification;
    }

    public String getText () {
        return text;
    }

    public int getId_utilisateur () {
        return id_utilisateur;
    }

    public String getType () {
        return type;
    }

    public byte[] getFichier () {
        return fichier;
    }


    public void setId_contenu (int id_contenu) {
        this.id_contenu = id_contenu;
    }

    public void setDate_publication (Date date_publication) {
        this.date_publication = date_publication;
    }

    public void setDate_modification (Date date_modification) {
        this.date_modification = date_modification;
    }

    public void setText (String text) {
        this.text = text;
    }

    public void setId_utilisateur (int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setType (String type) {
        this.type = type;
    }

    public void setFichier (byte[] fichier) {
        this.fichier = fichier;
    }
}
