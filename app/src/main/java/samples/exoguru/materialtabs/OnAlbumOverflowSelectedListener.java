package samples.exoguru.materialtabs;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by Milada on 02/05/2015.
 */
public class OnAlbumOverflowSelectedListener implements View.OnClickListener {
    private Context mContext;

    public OnAlbumOverflowSelectedListener(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        // This is an android.support.v7.widget.PopupMenu;
       /* PopupMenu popupMenu = new PopupMenu(mContext, v) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.album_overflow_delete:
                       // deleteAlbum(mAlbum);
                        return true;
                    case R.id.album_overflow_rename:
                       // renameAlbum(mAlbum);
                        return true;
                    case R.id.album_overflow_lock:
                       // lockAlbum(mAlbum);
                        return true;
                    case R.id.album_overflow_unlock:
                       // unlockAlbum(mAlbum);
                        return true;
                    case R.id.album_overflow_set_cover:
                      //  setAlbumCover(mAlbum);
                        return true;
                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };
        popupMenu.inflate(R.menu.popupmenu);
        popupMenu.show();*/

        PopupMenu popupMenu = new PopupMenu(mContext, v);
        popupMenu.inflate(R.menu.popupmenu);

        // Force icons to show
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popupMenu);
            argTypes = new Class[] { boolean.class };
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
            //popupMenu.getMenu().setGroupEnabled(R.id.album_overflow_lock, true);//removeItem(R.id.album_overflow_lock);

        } catch (Exception e) {
            // Possible exceptions are NoSuchMethodError and NoSuchFieldError
            //
            // In either case, an exception indicates something is wrong with the reflection code, or the
            // structure of the PopupMenu class or its dependencies has changed.
            //
            // These exceptions should never happen since we're shipping the AppCompat library in our own apk,
            // but in the case that they do, we simply can't force icons to display, so log the error and
            // show the menu normally.

            Log.w("TAG", "error forcing menu icons to show", e);
            popupMenu.show();
            return;
        }

        popupMenu.show();
    }
}