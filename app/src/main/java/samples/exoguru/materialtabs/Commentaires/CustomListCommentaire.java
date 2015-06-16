package samples.exoguru.materialtabs.Commentaires;

/**
 * Created by Milada on 16/06/2015.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import samples.exoguru.materialtabs.R;
import samples.exoguru.materialtabs.ServicesPackage.Commentaire;

public class CustomListCommentaire extends ArrayAdapter<Commentaire>{

    private final Activity context;
    private final ArrayList<Commentaire> coms;
    public CustomListCommentaire(Activity context,
                      ArrayList<Commentaire> coms) {
        super(context, R.layout.list_commentaire, coms);
        this.context = context;
        this.coms = coms;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_commentaire, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(coms.get(position).getCommentaire_text());

        imageView.setImageResource(R.drawable.un);
        return rowView;
    }
}