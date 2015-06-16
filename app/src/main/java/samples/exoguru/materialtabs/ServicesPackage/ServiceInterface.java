package samples.exoguru.materialtabs.ServicesPackage;

import android.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Milada on 27/05/2015.
 */
public class ServiceInterface {

    private static final String adresseSrv="192.168.1.7";

    public static void publier (Contenu content)
    {
        JSONObject contenuJson=new JSONObject();
        try {
            contenuJson.put("id_user",content.getId_utilisateur());
            contenuJson.put("status",content.getText());
            contenuJson.put("date_publication",content.getDate_publication().getTime());
            contenuJson.put("date_modification",content.getDate_modification().getTime());
            contenuJson.put("type",content.getType());
            HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/status/update");
            MultipartEntityBuilder entityBuilder =  MultipartEntityBuilder.create();
            entityBuilder.addBinaryBody("file",content.getFichier());
            entityBuilder.addTextBody("status",contenuJson.toString());
            req.setEntity(entityBuilder.build());
            HttpClient client=new DefaultHttpClient();
            HttpResponse reponse=client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifier(int id_content, Contenu newContent)
    {
        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/status/modifier");
        //var jsObj = {:_new_contenu,:_id_contenu}
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("new_contenu",newContent.getText());
            jsonObject.put("id_contenu",id_content);
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void commenter(int id_status, Commentaire comment,int id_user,Date date_publication)
    {
        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/contenu/commenter");
        //var jsObj = {date_publication: time}
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_user",id_user);
            jsonObject.put("id_status",id_status);
            jsonObject.put("commentaire",comment.getCommentaire_text());
            jsonObject.put("date_publication",date_publication.getTime());
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aimer(int id_status, boolean like,int id_user)
    {

        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/contenu/aimer");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_user",id_user);
            jsonObject.put("id_contenu",id_status);
            jsonObject.put("bool",like);
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerStatut(int id_status)
    {
        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/status/supprimer");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_status",id_status);
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void suivre(int id_followed,int id_user)
    {
        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/utilisateur/suivre");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id_follower",id_user);
            jsonObject.put("id_followed",id_followed);
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void moderer(int id_content, boolean accept)
    {
        HttpPost req = new HttpPost("http://"+adresseSrv+":8080/StatusService/api/contenu/moderer");
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("accepter",accept);
            jsonObject.put("contenu_id",id_content);
            StringEntity se = new StringEntity(jsonObject.toString());
            se.setContentType("application/json;charset=UTF-8");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
            req.setEntity(se);
            HttpClient client=new DefaultHttpClient();
            HttpResponse httpresponse = client.execute(req);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Contenu> getMur()
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://"+adresseSrv+":8080/StatusService/api/contenu/murSempel");
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            JSONArray finalResult=new JSONArray(content.toString());
            ArrayList<Contenu> listContenu=new ArrayList<Contenu>();
            for(int i=0;i<finalResult.length();i++){
                JSONObject jsonObject=finalResult.getJSONObject(i);
                Contenu cntenu=new Contenu();
                cntenu.setDate_modification(new Date(jsonObject.getLong("contenu_date_modification")));
                cntenu.setDate_publication(new Date(jsonObject.getLong("contenu_date_publication")));
                cntenu.setId_contenu(jsonObject.getInt("contenu_cle"));
                cntenu.setId_utilisateur(jsonObject.getInt("contenu_cle_utilisateur"));
                cntenu.setText(jsonObject.getString("contenu_text"));
                cntenu.setType(jsonObject.getString("contenu_type"));
                byte[] data= Base64.decode(jsonObject.getString("contenu_binaire"),Base64.DEFAULT);
                File fichier=new File(Integer.toString(jsonObject.getInt("contenu_cle")));
                FileOutputStream fos = new FileOutputStream(fichier);
                fos.write(data);
                fos.close();
                cntenu.setFichier(fichier);
                listContenu.add(cntenu);
            }
            return listContenu;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static ArrayList<Contenu> getContenuAModerer()
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;
        try {
            website = new URI("http://"+adresseSrv+":8080/StatusService/api/moderateur/contenu");
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            JSONArray finalResult=new JSONArray(content.toString());
            ArrayList<Contenu> listContenu=new ArrayList<Contenu>();
            for(int i=0;i<finalResult.length();i++){
                JSONObject jsonObject=finalResult.getJSONObject(i);
                Contenu cntenu=new Contenu();
                cntenu.setDate_modification(new Date(jsonObject.getLong("contenu_date_modification")));
                cntenu.setDate_publication(new Date(jsonObject.getLong("contenu_date_publication")));
                cntenu.setId_contenu(jsonObject.getInt("contenu_cle"));
                cntenu.setId_utilisateur(jsonObject.getInt("contenu_cle_utilisateur"));
                cntenu.setText(jsonObject.getString("contenu_text"));
                cntenu.setType(jsonObject.getString("contenu_type"));
                byte[] data= Base64.decode(jsonObject.getString("contenu_binaire"),Base64.DEFAULT);
                File fichier=new File(Integer.toString(jsonObject.getInt("contenu_cle")));
                FileOutputStream fos = new FileOutputStream(fichier);
                fos.write(data);
                fos.close();
                cntenu.setFichier(fichier);
                listContenu.add(cntenu);
            }
            return listContenu;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static ArrayList<Utilisateur> getListUtilisateurLike(int id_contenu)
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;

        try {
            website = new URI("http://"+adresseSrv+":8080/StatusService/api/contenu/utilisateur_like?id_contenu="+id_contenu);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            JSONArray finalResult=new JSONArray(content.toString());
            ArrayList<Utilisateur> listUtilisateur=new ArrayList<Utilisateur>();
            for(int i=0;i<finalResult.length();i++) {
                JSONObject jsonObject=finalResult.getJSONObject(i);
                Utilisateur user=new Utilisateur();
                user.setNom_utilisateur(jsonObject.getString("compte_user"));
                user.setUtilisateur_cle(jsonObject.getInt("cle_user"));
                listUtilisateur.add(user);
            }
            return listUtilisateur;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Commentaire> getListcommentaire(int id_contenu)
    {
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;

        try {
            website = new URI("http://"+adresseSrv+":8080/StatusService/api/contenu/commentaire?id_contenu="+id_contenu);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            JSONArray finalResult=new JSONArray(content.toString());
            ArrayList<Commentaire> listUtilisateur=new ArrayList<Commentaire>();
            for(int i=0;i<finalResult.length();i++) {
                JSONObject jsonObject=finalResult.getJSONObject(i);
                Commentaire com=new Commentaire();
                com.setCommentaire_text(jsonObject.getString("commentaireText"));
                com.setId_utilisateur(jsonObject.getInt("cle_utilisateur"));
                listUtilisateur.add(com);
            }
            return listUtilisateur;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Utilisateur getUser(int id_user){
        HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website = null;

        try {
            website = new URI("http://"+adresseSrv+":8080/StatusService/api/utilisateur/get_utilisateur?id_user="+id_user);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = rd.readLine())) {
                content.append(line);
            }
            JSONObject finalResult=new JSONObject(content.toString());
            Utilisateur user=new Utilisateur(finalResult.getInt("cle_user"),finalResult.getString("compte_user"));
            return user;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static byte[] fileToByteArray(File file)
    {

        //File file = new File("C:\\testing.txt");

        byte[] bFile = new byte[(int) file.length()];

        return bFile;
    }



    public static boolean statutDejaAime(int id_contenu)
    {
        return false;
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
