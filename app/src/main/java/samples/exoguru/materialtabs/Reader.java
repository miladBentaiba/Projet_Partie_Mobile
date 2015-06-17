package samples.exoguru.materialtabs;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by MohammedAmine on 17/04/2015.
 */
public class Reader extends AsyncTask<String, Void, Publication> {
    private IHolder holder;

    public String URL ="http://192.168.219.101:8080/StatusService/api/";

    public static String action ="";

    public Reader (IHolder holder)
    {
        this.holder = holder;
    }



    @Override
    protected Publication doInBackground (String... params) {

        switch (action)
        {

            case "poster":
            {
                Log.w("json","je suis dans poster: "+Environment.getExternalStorageState() );
                //Manipulation de l'image
                String path;
                File image;
               /* try
                {
                    path="/Racine/sdcard/Pictures/FlowShare.png";
                    image = new File(path);
                }
                catch(Exception e)*/
                {
                    // /Racine/sdcard/reminder.jpg
                    path = "/mnt/sdcard/reminder.jpg";//params[0];//Environment.getExternalStorageState()+"/Pictures/FlowShare.png";
                    image = new File(path);
                }
                String contenu = params[0];
                String date_modification = "20150412";
                String date_publication = "20150514";
                String type = null;
                int id_user = 1;
                try {
                    Log.w("json","dans try");
                    //Create new json object
                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("status",contenu);
                    jsonPost.put("date_modification",date_modification);
                    jsonPost.put("date_publication",date_publication);
                    jsonPost.put("id_user",id_user);
                    type="image";
                    jsonPost.put("type", "image");

                    Log.w("json", "HTTP client");
                    //Create http client
                    String url = this.URL+"status/update";
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppostreq = new HttpPost(url);
                    httppostreq.addHeader("Accept", "application/json");
                    httppostreq.addHeader("Content-Type", "application/json");
                    httppostreq.setHeader("Accept-Charset", "UTF-8");

                    //Create string entity
                    MultipartEntityBuilder mpe = MultipartEntityBuilder.create();//= new StringEntity(jsonPost.toString());
                    mpe.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    mpe.addBinaryBody("file", image);
                    try {
                        mpe.addPart("status",new StringBody(jsonPost.toString()));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    HttpEntity entity ;
                    entity= mpe.build();
                    httppostreq.setEntity(entity);



                    //type of the packet
                    //mpe.setContentType("application/json;charset=UTF-8");
                    //mpe.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));



                    Log.w("json", "fhgddhgfj");
                    //Execute POST request
                    HttpResponse httpresponse = httpclient.execute(httppostreq);
                    Log.w("json", "trying to get the response");
                    //get the response
                    String responseText ;
                    Log.w("json", "trying to get the response2");
                    JSONObject json =new JSONObject();
                    Log.w("json", "trying to get the response3");
                    try {
                        Log.w("json", "trying to get the response in the try");
                        responseText = EntityUtils.toString(httpresponse.getEntity());
                        json = new JSONObject(responseText);
                        Log.w("json", "response in try: "+json);
                    }
                    catch (ParseException e) {
                        Log.w("json", "response in catch: "+json);
                       Log.w("json", e.getMessage()+" ");
                    }
                    finally {
                        Log.w("json","results: "+json.toString());
                    }

                    return new Publication(contenu,date_modification, date_publication, id_user, type);
                }
                catch (Exception e)
                {
                    Log.w("json", e.getMessage()+" ");
                    return new Publication(contenu,date_modification, date_publication, id_user, type);
                }

            }
            //Avoir la liste des étudiants avec leurs données
            case "mur":
            {
                Log.w("json","je suis dans mur: "+Environment.getExternalStorageState() );
                //Manipulation de l'image
                String path="/Racine/sdcard/Pictures/FlowShare.png";
                File image = new File(path);


                String contenu = params[0];
                String date_modification = "20150412";
                String date_publication = "20150514";
                String type = null;
                int id_user = 1;
                try {
                    Log.w("json","dans try");
                    //Create new json object
                    JSONObject jsonPost = new JSONObject();
                    jsonPost.put("status",contenu);
                    jsonPost.put("date_modification",date_modification);
                    jsonPost.put("date_publication",date_publication);
                    jsonPost.put("id_user",id_user);
                    type="image";
                    jsonPost.put("type", "image");

                    Log.w("json", "HTTP client");
                    //Create http client
                    String url = this.URL+"status/update";
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppostreq = new HttpPost(url);
                    httppostreq.addHeader("Accept", "application/json");
                    httppostreq.addHeader("Content-Type", "application/json");
                    httppostreq.setHeader("Accept-Charset", "UTF-8");

                    //Create string entity
                    MultipartEntityBuilder mpe = MultipartEntityBuilder.create();//= new StringEntity(jsonPost.toString());
                    mpe.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    mpe.addBinaryBody("file", image);
                    try {
                        mpe.addPart("status",new StringBody(jsonPost.toString()));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    HttpEntity entity ;
                    entity= mpe.build();
                    httppostreq.setEntity(entity);



                    //type of the packet
                    //mpe.setContentType("application/json;charset=UTF-8");
                    //mpe.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));



                    Log.w("json", "fhgddhgfj");
                    //Execute POST request
                    HttpResponse httpresponse = httpclient.execute(httppostreq);
                    Log.w("json", "trying to get the response");
                    //get the response
                    String responseText ;
                    Log.w("json", "trying to get the response2");
                    JSONObject json =new JSONObject();
                    Log.w("json", "trying to get the response3");
                    try {
                        Log.w("json", "trying to get the response in the try");
                        responseText = EntityUtils.toString(httpresponse.getEntity());
                        json = new JSONObject(responseText);
                        Log.w("json", "response in try: "+json);
                    }
                    catch (ParseException e) {
                        Log.w("json", "response in catch: "+json);
                        Log.w("json", e.getMessage()+" ");
                    }
                    finally {
                        Log.w("json","results: "+json.toString());
                    }

                    return new Publication(contenu,date_modification, date_publication, id_user, type);
                }
                catch (Exception e)
                {
                    Log.w("json", e.getMessage()+" ");
                    return new Publication(contenu,date_modification, date_publication, id_user, type);
                }

            }
            //Avoir les données d'un étudiant
            case "one":
            {
                try {
                    String matricule = params[0];
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String url = "http://etudiantsapi.azurewebsites.net/api/etudiants/" + matricule;
                    HttpGet get = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(get);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String response = EntityUtils.toString(httpEntity);
                    JSONObject jsonObj = new JSONObject(response);
                    String nom = jsonObj.getString("Nom");
                    String prenom = jsonObj.getString("Prenom")+" "+
                            jsonObj.getString("Matricule")+" "+
                            jsonObj.getString("NoteTD")+" "+
                            jsonObj.getString("Moyenne")+" "+
                            jsonObj.getString("NoteProjet");
                    return new Publication();
                }
                catch (Exception e)
                {
                    return null;
                }
            }
            //Avoir la liste des étudiants avec leurs données
            case "liste":
            {
                try {
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    String url = "http://etudiantsapi.azurewebsites.net/api/etudiants/" ;//+ matricule;
                    HttpGet get = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(get);
                    HttpEntity httpEntity = httpResponse.getEntity();
                    String response = EntityUtils.toString(httpEntity);
                    JSONArray jsonObj = new JSONArray(response);
                    String resultat ="";
                    try
                    {
                        int i = 0;
                        while (true)
                        {

                            JSONObject obj = jsonObj.getJSONObject(i);
                            resultat= resultat +
                                    obj.getString("Prenom")+" "+
                                    obj.getString("Nom")+" "+
                                    obj.getString("Matricule")+" "+
                                    obj.getString("NoteTD")+" "+
                                    obj.getString("Moyenne")+" "+
                                    obj.getString("NoteProjet")+"\n";

                            i++;
                        }
                    }
                    catch(Exception e)
                    {
                        return new Publication();
                    }
                    finally {
                        return new Publication();
                    }
                }
                catch (Exception e)
                {
                    return null;
                }
            }
            //Insérer un nouveau étudiant
            case "insert":
            {
                try {
                        //Create new json object
                        JSONObject jsonPost = new JSONObject();
                        jsonPost.put("Nom","Miladus");
                        jsonPost.put("Prenom","added_by_milad");
                        jsonPost.put("Matricule","110121");
                        jsonPost.put("NoteTD","20");
                        jsonPost.put("NoteProjet","20");


                        //Create http client
                        String url = "http://etudiantsapi.azurewebsites.net/api/etudiants/" ;
                        DefaultHttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppostreq = new HttpPost(url);

                        //Create string entity
                        StringEntity se = new StringEntity(jsonPost.toString());

                        //type of the packet
                        se.setContentType("application/json;charset=UTF-8");
                        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

                        //Set entity in post request
                        httppostreq.setEntity(se);

                        //Execute POST request
                        HttpResponse httpresponse = httpclient.execute(httppostreq);

                        //get the response
                        String responseText ;
                        try {
                            responseText = EntityUtils.toString(httpresponse.getEntity());
                            JSONObject json = new JSONObject(responseText);
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                }
                catch (Exception e)
                {
                    return null;
                }
            }
            case "modify":
            {
                try {
                        Log.w("modif","get to modify step one");
                        //Create new json object
                        JSONObject jsonPut = new JSONObject();
                        jsonPut.put("Nom","i_am_Milad");
                        jsonPut.put("Prenom","Miladu");
                        jsonPut.put("Matricule","110119");
                        jsonPut.put("NoteTD","22");
                        jsonPut.put("NoteProjet", "21");

                    Log.w("modif","json object created");
                        //Create http client
                        String matricule = params[0];
                        String url = "http://etudiantsapi.azurewebsites.net/api/etudiants/"+matricule ;
                        DefaultHttpClient httpclient = new DefaultHttpClient();
                        HttpPut httpputreq = new HttpPut(url);

                    Log.w("modif","http client created");
                        //Specify the type of the json packet
                        httpputreq.addHeader("Accept", "application/json");
                        httpputreq.addHeader("Content-Type", "application/json");

                    Log.w("modif","type of json defined");
                        //Create string entity
                        StringEntity se = new StringEntity(jsonPut.toString());

                    Log.w("modif","define type a second time");
                        //type of the packet
                        se.setContentType("application/json;charset=UTF-8");
                        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

                    //Resolving problems
                         se.setContentEncoding("UTF-8");
                        httpputreq.setHeader("Content-Type","application/json; charset=UTF-8");
                        httpputreq.setEntity(se);


                    Log.w("modif","execute the query");
                        //Execute POST request
                        HttpResponse httpresponse = httpclient.execute(httpputreq);

                    Log.w("modif","get results");
                        //get the response
                        String responseText ;
                        try {
                            responseText = EntityUtils.toString(httpresponse.getEntity());
                            Log.w("modif","one");
                            Log.w("modif","response: "+responseText);
                            Log.w("modif","two");
                            JSONObject json = new JSONObject(responseText);
                            Log.w("modif","three");
                            Log.w("modif","response two: "+json.toString());
                            Log.w("modif","end");
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                            Log.i("json", e + " parse exception");
                        }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                return new Publication();
            }
            default: return null;
        }

    }

    @Override
    protected void onPostExecute(Publication publication)
    {
        holder.setEtudiant(publication);
    }


}
