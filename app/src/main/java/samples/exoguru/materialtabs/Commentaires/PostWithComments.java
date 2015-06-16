package samples.exoguru.materialtabs.Commentaires;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import samples.exoguru.materialtabs.OnAlbumOverflowSelectedListener;
import samples.exoguru.materialtabs.R;
import samples.exoguru.materialtabs.ServicesPackage.Contenu;
import samples.exoguru.materialtabs.ServicesPackage.ServiceInterface;

public class PostWithComments extends ActionBarActivity {


    ListView list;



    private MediaController mediaControls;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_with_comments);
        int id_post = 0;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id_post = extras.getInt("idPost");
            Log.w("service", "id_post: "+id_post);

            String nom = extras.getString("Nom");



        }

        final Contenu c = ServiceInterface.getContenu(id_post);

        EditText commentaire;
        TextView nom_user;
        TextView time;
        TextView publication;
        ImageView menu;
        VideoView myVideoView;
        ImageView img;


        commentaire = (EditText) findViewById(R.id.commentaire);
        nom_user = (TextView) findViewById(R.id.user);
        time = (TextView) findViewById(R.id.time);
        publication = (TextView) findViewById(R.id.text);
        menu = (ImageView) findViewById(R.id.menu);
        myVideoView = (VideoView) findViewById(R.id.video_view);
        img = (ImageView)findViewById(R.id.images);

        nom_user.setText(
                ServiceInterface.getUser(c.getId_utilisateur()).
                        getNom_utilisateur());
        time.setText(c.getDate_publication().toString());
        publication.setText(c.getText());
        menu.setOnClickListener(new OnAlbumOverflowSelectedListener(PostWithComments.this,
                ServiceInterface.getContenu(id_post)));
        if (c.getType()=="Image")
        {
            img.setVisibility(View.VISIBLE);
            myVideoView.setVisibility(View.GONE);
            byte[] imageByte = ServiceInterface.fileToByteArray(c.getFichier());
            img.setImageBitmap(
                    BitmapFactory.decodeByteArray(imageByte, 0,
                            imageByte.length));
        }
        else if(c.getType()=="Video")
        {
            img.setVisibility(View.GONE);
            myVideoView.setVisibility(View.VISIBLE);
            if (mediaControls == null) {
                mediaControls = new MediaController(this);
            }
            //myVideoView.setMediaController(mediaControls);


                //initialize the VideoView
                try {
                    //set the uri of the add_video to be played
                    myVideoView.setVideoURI(Uri.parse("android.resource://" +
                            getPackageName() + "/" + R.raw.avion));
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

        }
        else
        {
            img.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
        }

        ImageView aimerButton = (ImageView) findViewById(R.id.jaime);
        if (ServiceInterface.statutDejaAime(c.getId_contenu()))
        {
            aimerButton.setImageResource(R.drawable.rating_good_good);
        }
        else
        {
            aimerButton.setImageResource(R.drawable.rating_good);
        }

        /*aimerButton = (ImageView) findViewById(R.id.jaime);
        final ImageView finalAimerButton = aimerButton;
        aimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                ServiceInterface.aimer(
                        c.getId_contenu(),
                        ServiceInterface.statutDejaAime(c.getId_contenu()));
                if (ServiceInterface.statutDejaAime(c.getId_contenu()))
                {
                    finalAimerButton.setImageResource(R.drawable.rating_good_good);
                }
                else
                {
                    finalAimerButton.setImageResource(R.drawable.rating_good);
                }
            }
        });*/



        //ListView of comments
        CustomListCommentaire adapter = new
                CustomListCommentaire(PostWithComments.this,
                ServiceInterface.getListcommentaire(id_post));
        list=(ListView)findViewById(R.id.commentList);
        list.setAdapter(adapter);


        list.setClickable(false);
        list.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        onDraw(new Canvas());



    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_with_comments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int old_count;


    protected void onDraw(Canvas canvas) {

        ViewGroup.LayoutParams params;

        if (list.getCount() != old_count) {
            params = list.getLayoutParams();
            old_count = list.getCount();
            int totalHeight = 0;
            for (int i = 0; i < list.getCount(); i++) {
                list.measure(0, 0);
                totalHeight += list.getMeasuredHeight();
            }

            params = list.getLayoutParams();
            params.height = totalHeight + (list.getDividerHeight() * (list.getCount() - 1));
            list.setLayoutParams(params);
        }
    }



}
