package samples.exoguru.materialtabs;


/**
 * Created by Milada on 01/05/2015.
 */
/**
 * Created by Milada on 01/05/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

import samples.exoguru.materialtabs.Commentaires.PostWithComments;
import samples.exoguru.materialtabs.ServicesPackage.Contenu;
import samples.exoguru.materialtabs.ServicesPackage.ServiceInterface;

public class CustomList extends ArrayAdapter<Contenu>  {

    private ArrayList<Contenu> contenu;

    private MediaController mediaControls;
    //EditText
    ArrayList<String> myItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public CustomList(Activity context,
                      ArrayList<Contenu> contenu) {
        super(context, R.layout.list_item_card,contenu);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contenu = contenu;


        for (int i=0; i<contenu.size(); i++)
        {
            myItems.add("");
        }
        notifyDataSetChanged();
    }
    public int getCount() {
        return myItems.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return 500;
    }*/

    class ViewHolder {
        EditText commentaire;
        TextView nom_user;
        TextView time;
        TextView publication;
        ImageView menu;
        VideoView myVideoView;
        ImageView img;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_card, null);
            holder.commentaire = (EditText) convertView.findViewById(R.id.commentaire);
            holder.nom_user = (TextView) convertView.findViewById(R.id.user);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.publication = (TextView) convertView.findViewById(R.id.text);
            holder.menu = (ImageView) convertView.findViewById(R.id.menu);
            holder.myVideoView = (VideoView) convertView.findViewById(R.id.video_view);
            holder.img = (ImageView)convertView.findViewById(R.id.images);

            holder.nom_user.setText(
                    ServiceInterface.getUser(contenu.get(position).getId_utilisateur()).
                            getNom_utilisateur());
            holder.time.setText(contenu.get(position).getDate_publication().toString());
            holder.publication.setText(contenu.get(position).getText());
            holder.menu.setOnClickListener(new OnAlbumOverflowSelectedListener(getContext()));
            if (contenu.get(position).getType().contains("image"))
            {
                holder.img.setVisibility(View.VISIBLE);
                holder.myVideoView.setVisibility(View.GONE);
                byte[] imageByte = ServiceInterface.fileToByteArray(contenu.get(position).getFichier());
                holder.img.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageByte, 0,
                                imageByte.length));
            }
            else if(contenu.get(position).getType()=="Video")
            {
                holder.img.setVisibility(View.GONE);
                holder.myVideoView.setVisibility(View.VISIBLE);
                if (mediaControls == null) {
                    mediaControls = new MediaController(convertView.getContext());
                }
                //holder.myVideoView.setMediaController(mediaControls);

                    holder.img.setVisibility(View.GONE);
                    //set the media controller buttons

                    //initialize the VideoView
                    try {
                        //set the uri of the add_video to be played
                    holder.myVideoView.setVideoURI(Uri.parse("android.resource://" +
                            convertView.getContext().getPackageName() + "/" + R.raw.avion));
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
            }
            else
            {
                holder.img.setVisibility(View.GONE);
                holder.img.setVisibility(View.GONE);
            }

            ImageView aimerButton = (ImageView) convertView.findViewById(R.id.jaime);
            if (ServiceInterface.statutDejaAime(contenu.get(position).getId_contenu()))
            {
                aimerButton.setImageResource(R.drawable.rating_good_good);
            }
            else
            {
                aimerButton.setImageResource(R.drawable.rating_good);
            }
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        holder.commentaire.setText("");
        holder.commentaire.setId(position);



       /* //we need to update adapter once we finish with editing
        holder.commentaire.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange (View v, boolean hasFocus) {
                if (!hasFocus) {
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    web[position] = Caption.getText().toString();
                }
            }
        });*/


        ImageView commenterButton = (ImageView) convertView.findViewById(R.id.commenter);
        final View finalConvertView = convertView;
        commenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(getContext(), PostWithComments.class);
                intent.putExtra("idPost", contenu.get(position).getId_utilisateur());
                finalConvertView.getContext().startActivity(intent);
            }
        });

        final ImageView aimerButton = (ImageView) convertView.findViewById(R.id.jaime);
        aimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                ServiceInterface.aimer(
                        contenu.get(position).getId_contenu(),
                        ServiceInterface.statutDejaAime(contenu.get(position).getId_contenu()),
                        contenu.get(position).getId_utilisateur());
                if (ServiceInterface.statutDejaAime(contenu.get(position).getId_contenu()))
                {
                    aimerButton.setImageResource(R.drawable.rating_good_good);
                }
                else
                {
                    aimerButton.setImageResource(R.drawable.rating_good);
                }
            }
        });


        return  convertView;
    }
}