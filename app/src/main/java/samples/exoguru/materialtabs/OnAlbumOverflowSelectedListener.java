package samples.exoguru.materialtabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

import samples.exoguru.materialtabs.ServicesPackage.Contenu;
import samples.exoguru.materialtabs.ServicesPackage.ServiceInterface;
import samples.exoguru.materialtabs.UserSession.User;

/**
 * Created by Milada on 02/05/2015.
 */
public class OnAlbumOverflowSelectedListener implements View.OnClickListener {
    private Context mContext;
    private Contenu contenu;

    public OnAlbumOverflowSelectedListener(Context context, Contenu contenu) {
        mContext = context;
        this.contenu = contenu;
    }

    @Override
    public void onClick(View v) {
        // This is an android.support.v7.widget.PopupMenu;
       PopupMenu popupMenu = new PopupMenu(mContext, v) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Log.w("service","user called: "+ contenu.getId_utilisateur());
                switch (item.getItemId()) {
                    case R.id.suivre:
                       suivre(contenu.getId_utilisateur());
                        return true;
                    case R.id.modify:
                       modifier(contenu);
                        return true;
                    case R.id.supprimer:
                       supprimer(contenu.getId_contenu(), contenu.getId_utilisateur());
                        return true;
                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };
        popupMenu.inflate(R.menu.popupmenu);
        //popupMenu.show();
        //PopupMenu popupMenu = new PopupMenu(mContext, v);
        // Force icons to show
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[] { boolean.class };
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
            //popupMenu.getMenu().setGroupnabled(R.id.album_overflow_lock, true);
            if (contenu.getId_utilisateur()==Integer.parseInt(User.getInstance().getID()))
            {
                popupMenu.getMenu().removeItem(R.id.suivre);
            }
            else
            {
                popupMenu.getMenu().removeItem(R.id.modify);
                popupMenu.getMenu().removeItem(R.id.supprimer);
            }
            // removeItem(R.id.album_overflow_lock);
        } catch (Exception e) {
            Log.w("TAG", "error forcing menu icons to show", e);
            popupMenu.show();
            return;
        }
        popupMenu.show();
    }

    private void suivre(int id_user)
    {
        try
        {
            int id_suivi = Integer.parseInt(User.getInstance().getID());
            if(id_suivi==id_user)
            {
                Toast.makeText(mContext, "Vous ne pouvez pas suivre votre propre publications",
                        Toast.LENGTH_LONG).show();

            }
            else
            {
                ServiceInterface.suivre(id_suivi, id_user);
            }
        }
        catch(Exception e)
        {
            Log.e("service", "Exception getting user from local");
        }

    }

    private void supprimer(int id_contenu, int id_user)
    {
        int local_user = Integer.parseInt(User.getInstance().getID());
        if(id_user==local_user)
        {
            ServiceInterface.supprimerStatut(id_contenu);
        }
        else
        {
            Toast.makeText(mContext, "Vous ne pouvez pas supprimer cette publication",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void modifier(Contenu contenu)
    {
        Intent intent = new Intent(mContext,ModifierPost.class );
        intent.putExtra("User", contenu.getId_utilisateur());
        intent.putExtra("DatePublication", contenu.getDate_publication());
        intent.putExtra("Type", contenu.getType());
        intent.putExtra("IDContent",contenu.getId_contenu());
        intent.putExtra("Fichier", contenu.getFichier());
        intent.putExtra("Text", contenu.getText());
        mContext.startActivity(intent);
    }
}