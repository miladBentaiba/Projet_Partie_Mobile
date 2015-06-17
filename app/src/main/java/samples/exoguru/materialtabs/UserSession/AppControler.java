package samples.exoguru.materialtabs.UserSession;

import java.util.ArrayList;

import samples.exoguru.materialtabs.ServicesPackage.Contenu;

/**
 * Created by Milada on 17/06/2015.
 */
public class AppControler {


    private ArrayList<Contenu>  murPosts ;

    User user ;

    private AppControler (ArrayList<Contenu> murPosts, User user) {
        this.murPosts = murPosts;
        this.user = user;

    }



    private static AppControler instance ;

    public ArrayList<Contenu> getMurPosts () {
        return murPosts;
    }

    public void setMurPosts (ArrayList<Contenu> murPosts) {
        this.murPosts = murPosts;
    }

    public User getUser () {
        return user;
    }

    public void addContent(Contenu c)
    {
        this.murPosts.add(c);
    }

    public void setUser (User user) {
        this.user = user;
    }

    public static AppControler getInstance () {
        return instance ;
    }

    public static void setInstance (ArrayList<Contenu>  contenus ,User user ) {
        AppControler.instance = new AppControler(contenus , user);
    }
}
