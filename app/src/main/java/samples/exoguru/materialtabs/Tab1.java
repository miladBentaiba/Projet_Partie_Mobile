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

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab1 extends Fragment {


    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] image ={
            R.drawable.un,
            R.drawable.deux,
            R.drawable.troix,
            R.drawable.menu,
            R.drawable.social_chat,
            R.drawable.rating_good,
            R.drawable.navigation_expand
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        CustomList adapter = new
                CustomList(this.getActivity(), web, image);
        list=(ListView)v.findViewById(R.id.list);
        list.setClickable(true);
        list.addHeaderView(new View(this.getActivity()));
        list.addFooterView(new View(this.getActivity()));
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Tab1.this.getActivity(), "You Clicked at " + web[position - 1], Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}
