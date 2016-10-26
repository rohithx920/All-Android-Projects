package com.example.rohith.inclass_08;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

Activity activity;
    public ListFragment() {
        this.activity=MainActivity.mainActivity;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        new GetData(MainActivity.mainActivity).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml");
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lv = (ListView) getActivity().findViewById(R.id.listView);

    }
    public interface next123{
        void nextvalue(String image,String name);
    }


}
