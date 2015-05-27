package samples.exoguru.materialtabs.ServicesPackage;

/**
 * Created by Milada on 27/05/2015.
 */
public class ServiceInterface {

    public void publier (Contenu content)
    {

    }

    public void modifier(int id_content, Contenu newContent)
    {

    }

    public void commenter(int id_commentaire, Commentaire comment)
    {

    }

    public void aimer(int id_aimer, boolean like)
    {

    }

    public void supprimerStatut(int id_contnent)
    {

    }


    public void suivre(int id_user)
    {

    }

    public void moderer(int id_content, boolean accept)
    {

    }

    public Contenu[] getMur()
    {
        Contenu [] c = {
            new Contenu(),
            new Contenu()
        };
        return c;
    }

    public Contenu[] getContenuAModerer()
    {
        Contenu [] c = {
                new Contenu(),
                new Contenu()
        };
        return c;
    }

    public Utilisateur[] getListUtilisateurLike(int id_contenu)
    {
        Utilisateur [] c = {
                new Utilisateur(),
                new Utilisateur()
        };
        return c;

    }

    public String[] getListcommentaire(int id_contenu)
    {
        String[] c ={"milad", "besma"};
        return c;
    }

    public Utilisateur getUser(int id_user)
    {
        return new Utilisateur();
    }
}
