package samples.exoguru.materialtabs;

/**
 * Created by MohammedAmine on 17/04/2015.
 */
public class Publication {
    private String status;
    private String date_modification;
    private String date_publication;
    private int id_user;
    private String type =null;

    public Publication(){}

    public Publication (String status, String date_modification,
                        String date_publication, int id_user, String type)
    {
        this.status = status;
        this.date_modification =date_modification;
        this.date_publication =date_publication;
        this.id_user =id_user;
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public String getDate_modification()
    {
        return date_modification;
    }
    public String getDate_publication()
    {
        return date_publication;
    }
    public String getStatus()
    {
        return status;
    }
    public int getId_user()
    {
        return id_user;
    }

}
