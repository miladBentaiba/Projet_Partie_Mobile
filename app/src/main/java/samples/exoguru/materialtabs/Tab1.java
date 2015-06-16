package samples.exoguru.materialtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import samples.exoguru.materialtabs.ServicesPackage.ServiceInterface;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {

    ListView list;
    CustomList adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        adapter = new CustomList(this.getActivity(), ServiceInterface.getMur());
        list=(ListView)v.findViewById(R.id.list);
        list.setClickable(true);
        list.addHeaderView(new View(this.getActivity()));
        list.addFooterView(new View(this.getActivity()));
        list.setAdapter(adapter);

        return v;
    }
}
