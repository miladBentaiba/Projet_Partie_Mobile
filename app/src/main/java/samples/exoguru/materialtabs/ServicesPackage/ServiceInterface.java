package samples.exoguru.materialtabs.ServicesPackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;

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

    public static void aimer(int id_contenu, boolean like, int id_user)
    {

    }

    public static boolean statutDejaAime(int id_contenu)
    {
        return false;
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
        content.add(new Contenu(1,new Date(20151204),
                new Date(20151230),"contenu published",1,"****",null));
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
        coms.add(new Commentaire(0,"premier commentaire",1,1));
        coms.add(new Commentaire(1,"deuxieme commentaire commentaire",1,1));
        coms.add(new Commentaire(2,"troixieme commentaire commentaire commentaire",1,1));
        coms.add(new Commentaire(3,"troixième commentaire omslic static ArrayList<Utilisateur> "+
                "getListU.add(new Commentantenu(1,new Date(2015ire(0 "+
                "commentairelic static ArrayList<Utilisateur> getListU commentaire",1,1));

        return coms;

    }

    public static Utilisateur getUser(int id_user)
    {
        return new Utilisateur(id_user,"Milad");
    }


    public static byte[] fileToByteArray(File file)
    {

        //File file = new File("C:\\testing.txt");

        byte[] bFile = new byte[(int) file.length()];

        return bFile;
    }

    public static Contenu getContenu(int id_contenu)
    {
        if (id_contenu == 1)
            return new Contenu(1,new Date(20151204),
                    new Date(20151230),"contenu published",1,"****",null);
        else
            return new Contenu();
    }

}
