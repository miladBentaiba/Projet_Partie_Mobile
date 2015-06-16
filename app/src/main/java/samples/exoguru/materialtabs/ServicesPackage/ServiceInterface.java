package samples.exoguru.materialtabs.ServicesPackage;

import java.util.ArrayList;

/**
 * Created by Milada on 27/05/2015.
 */
public class ServiceInterface {

    public static void publier (Contenu content)
    {

    }

    public static void modifier(int id_content, Contenu newContent)
    {

    }

    public static void commenter(int id_commentaire, Commentaire comment)
    {

    }

    public static void aimer(int id_aimer, boolean like)
    {

    }

    public static void supprimerStatut(int id_contnent)
    {

    }


    public static void suivre(int id_user)
    {

    }

    public static void moderer(int id_content, boolean accept)
    {

    }

    public static ArrayList<Contenu> getMur()
    {
        ArrayList<Contenu>  content= new ArrayList<>();
        return content;
    }

    public static ArrayList<Contenu> getContenuAModerer()
    {
        ArrayList<Contenu>  content= new ArrayList<>();
        return content;
    }

    public static ArrayList<Utilisateur> getListUtilisateurLike(int id_contenu)
    {
        ArrayList<Utilisateur>  users= new ArrayList<>();
        return users;

    }

    public static ArrayList<Commentaire> getListcommentaire(int id_contenu)
    {
        ArrayList<Commentaire> coms = new ArrayList<>();
        return coms;
    }

    public static Utilisateur getUser(int id_user)
    {
        return new Utilisateur();
    }


}
