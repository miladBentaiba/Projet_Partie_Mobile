package samples.exoguru.materialtabs.UserSession;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Sarra on 17/05/2015.
 */
public class User {
    private String ID ;
    private String name;
    private String mail ;
    private String imageUrl ;
    private String token ;

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getToken() {
        return token;
    }

    public static User getInstance() {

        return instance;
    }

    private static User instance;

    public static User createInstance(String ID , String name , String mail , String imageUrl, String token)
    {
        if (instance == null)
        {
            instance = new User(ID , name , mail , imageUrl , token);
        }
        return instance ;
    }
    private User(String ID , String name , String mail , String imageUrl , String token ) {
        this.ID = ID;
        this.mail =mail ;
        this.name = name ;
        this.imageUrl = imageUrl ;
    }

    //new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
