package samples.exoguru.materialtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import samples.exoguru.materialtabs.ServicesPackage.Contenu;
import samples.exoguru.materialtabs.ServicesPackage.ServiceInterface;
import samples.exoguru.materialtabs.UserSession.AppControler;
import samples.exoguru.materialtabs.UserSession.User;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

    ListView list;
    static CustomList adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);

        ArrayList<Contenu> contentList = ServiceInterface.getMur() ;
        AppControler.setInstance(contentList , User.getInstance());
        adapter = new CustomList(this.getActivity(), AppControler.getInstance().getMurPosts());
        list=(ListView)v.findViewById(R.id.list);
        list.setClickable(true);
        list.addHeaderView(new View(this.getActivity()));
        list.addFooterView(new View(this.getActivity()));

        list.setAdapter(adapter);
        System.out.println("onCreateView");
        return v;
    }

    @Override
    public void onResume () {
        System.out.println("onResume size : "+ adapter.contenu.size());
        adapter.notifyDataSetChanged();
        super.onResume();

    }
}
