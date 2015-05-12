package samples.exoguru.materialtabs;

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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String> {

    private final String[] web;
    private final Integer[] image;
    private VideoView myVideoView;
    private MediaController mediaControls;
    private int posity = -1;
    //EditText
    ArrayList<String> myItems = new ArrayList<>();
    private LayoutInflater mInflater;

    public CustomList(Activity context,
                      String[] web, Integer[] image) {
       super(context, R.layout.list_item_card, web);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.web = web;
        this.image =image;

        for (int i=0; i<web.length; i++)
        {
            myItems.add("");
        }
        notifyDataSetChanged();
    }
    public int getCount() {
        return myItems.size();
    }

   class ViewHolder {
       EditText caption;
   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_card, null);
            holder.caption = (EditText) convertView.findViewById(R.id.commentaire);
            TextView txtTitle1 = (TextView) convertView.findViewById(R.id.text1);
            TextView txtTitle2 = (TextView) convertView.findViewById(R.id.text2);
            TextView txtTitle3 = (TextView) convertView.findViewById(R.id.text3);
            ImageView menu = (ImageView) convertView.findViewById(R.id.menu);
            myVideoView = (VideoView) convertView.findViewById(R.id.video_view);
            ImageView img = (ImageView)convertView.findViewById(R.id.images);
            txtTitle1.setText(web[position]);
            txtTitle2.setText(web[position]);
            txtTitle3.setText(web[position]);
            menu.setOnClickListener(new OnAlbumOverflowSelectedListener(getContext()));
            img.setImageResource(image[position]);
            if(position==2)
            {
                img.setVisibility(View.GONE);
                //set the media controller buttons
                if (mediaControls == null) {
                    mediaControls = new MediaController(convertView.getContext());
                }
                //initialize the VideoView
                try {
                    //set the media controller in the VideoView
                    myVideoView.setMediaController(mediaControls);
                    //set the uri of the video to be played
                    myVideoView.setVideoURI(Uri.parse("android.resource://" +
                            convertView.getContext().getPackageName() + "/" + R.raw.avion));
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            else
            {
                myVideoView.setVisibility(View.GONE);
            }
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        //Fill EditText with the value you have in data source
        holder.caption.setText("");
        holder.caption.setId(position);

        //we need to update adapter once we finish with editing
        holder.caption.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    final int position = v.getId();
                    final EditText Caption = (EditText) v;
                    web[position] = Caption.getText().toString();
                }
            }
        });

        return  convertView;
    }
}