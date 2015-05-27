package samples.exoguru.materialtabs;


/**
 * Created by Milada on 01/05/2015.
 */
/**
 * Created by Milada on 01/05/2015.
 */
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;
import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>  {

    private final String[] web;
    private final Integer[] image;
    private MediaController mediaControls;
    //EditText
    ArrayList<String> myItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public CustomList(Activity context,
                      String[] data, Integer[] image) {
        super(context, R.layout.list_item_card, data);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.web = data;
        this.image =image;

        for (int i=0; i<data.length; i++)
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
        //VideoView myVideoView;
        ImageView img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
            //holder.myVideoView = (VideoView) convertView.findViewById(R.id.video_view);
            holder.img = (ImageView)convertView.findViewById(R.id.images);
            holder.nom_user.setText(web[position]);
            holder.time.setText(web[position]);
            holder.publication.setText(web[position]);
            holder.menu.setOnClickListener(new OnAlbumOverflowSelectedListener(getContext()));
            holder.img.setImageResource(image[position]);
            if (mediaControls == null) {
                mediaControls = new MediaController(convertView.getContext());
            }
            //holder.myVideoView.setMediaController(mediaControls);
            if(position==1)
            {
                holder.img.setVisibility(View.GONE);
                //set the media controller buttons

                //initialize the VideoView
                try {
                    //set the uri of the add_video to be played
                    /*holder.myVideoView.setVideoURI(Uri.parse("android.resource://" +
                            convertView.getContext().getPackageName() + "/" + R.raw.avion));*/
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
           /* else
            {
                holder.myVideoView.setVisibility(View.GONE);
            }*/
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        holder.commentaire.setText("");
        holder.commentaire.setId(position);



        //we need to update adapter once we finish with editing
        holder.commentaire.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange (View v, boolean hasFocus) {
                if (!hasFocus) {
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    web[position] = Caption.getText().toString();
                }
            }
        });

        return  convertView;
    }
}