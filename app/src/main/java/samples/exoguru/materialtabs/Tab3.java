package samples.exoguru.materialtabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab3 extends Fragment implements IHolder {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_3,container,false);

        Button poster = (Button)view.findViewById(R.id.poster);
        final IHolder holder = this;
        poster.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                View parent = getView();
                Log.w("json", "eta1");
                EditText txt = (EditText)parent.findViewById(R.id.txtMatricule);
                Log.w("json", "eta2");
                String matricule = txt.getText().toString();
                Log.w("json", "eta3");
                Reader.action = "poster";
                new Reader(holder).execute(matricule);
                Log.w("json", "finished");
            }
        });
        Button mur = (Button) view.findViewById(R.id.mur);
        mur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                View parent = getView();
                Log.w("json", "eta1");
                EditText txt = (EditText)parent.findViewById(R.id.txtMatricule);
                Log.w("json", "eta2");
                String matricule = txt.getText().toString();
                Log.w("json", "eta3");
                Reader.action = "mur";
                new Reader(holder).execute(matricule);
                Log.w("json", "finished");
            }
        });
        Button modifier = (Button) view.findViewById(R.id.modify);
        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                View parent = getView();
                Log.w("json", "eta1");
                EditText txt = (EditText)parent.findViewById(R.id.txtMatricule);
                Log.w("json", "eta2");
                String matricule = txt.getText().toString();
                Log.w("json", "eta3");
                Reader.action = "modify";
                new Reader(holder).execute(matricule);
                Log.w("json", "finished");
            }
        });


        Button modif = (Button) view.findViewById(R.id.modify);
        modif.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                View parent = getView();
                Log.w("modif", "eta1");
                EditText txt = (EditText)parent.findViewById(R.id.txtMatricule);
                Log.w("modif", "eta2");
                String matricule = txt.getText().toString();
                Log.w("modif", "eta3");
                Reader.action = "modify";
                new Reader(holder).execute(matricule);
                Log.w("modif", "finished");
            }
        });

        return view;
    }


    @Override
    public void setEtudiant(Publication e) {
        View view = Tab3.this.getView();
        TextView txtNom =(TextView)view.findViewById(R.id.txtNom);

        if(e == null)
            txtNom.setText("Erreur");
        else
            txtNom.setText("Statut: "+e.getStatus() +
                    "\ndate_modification: " + e.getDate_modification()+
                    "\ndate_publication: "+e.getDate_publication()+
                    "\nid_user: "+e.getId_user()+
                    "\ntype: "+e.getType());
    }
}
