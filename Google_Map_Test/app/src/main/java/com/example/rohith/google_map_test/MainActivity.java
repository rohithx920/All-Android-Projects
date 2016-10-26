package com.example.rohith.google_map_test;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AutoCompletionFragment.SendValuesBackToActivity {
    EditText et1,et2,et3;
    ArrayList<String> locations;
    String TAG="demo";
    static MainActivity mainActivity;
    int i;
    //Fragment Pla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2_layout);
        mainActivity=this;
        for(i=1;i<=3;i++) {
            getFragmentManager().beginTransaction().add(R.id.container_scrollview, new AutoCompletionFragment(), "fragment_tag_one"+i)
                    .commit();
        }
        if(i==4){
            --i;
        }
            //getFragmentManager().beginTransaction().add(R.id.container, new AutoCompletionFragment(), "fragment_tag_two")
              //  .commit();
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag(""));

        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i=new Intent();
                String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+et1.getText()+ "&destination="+et2.getText()+"&key=AIzaSyApfn0IRy81vEVRPSA1DXZUqGO_27vcXNE";
                new GetData(MainActivity.this).execute(url);
                //i.putExtra("s",et1.getText().toString());
                //i.putExtra("d",et2.getText().toString());
                //startActivity(i);
            }
        }); */
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To display map pass to map Activity with object or arraylist of values
                //--i;
                locations=new ArrayList<String>();
                while(i>0){
                    Log.d(TAG, ""+i);
                    AutoCompletionFragment af= (AutoCompletionFragment) getFragmentManager().findFragmentByTag("fragment_tag_one"+i);
                    EditText et2= (EditText) af.getView().findViewById(R.id.editText5);
                    locations.add(et2.getText().toString());
                    Log.d(TAG, "onClick: "+locations.size()+"---"+et2.getText().toString());
                    --i;
                }
                new GetData(MainActivity.this).execute(locations);
                //dataGetter(locations);


            }
        });
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To add the fragments dynamically to the scroll view.

                i++;//incrementing i
                getFragmentManager().beginTransaction().add(R.id.container_scrollview, new AutoCompletionFragment(), "fragment_tag_one"+i)
                        .commit();

            }
        });
    }

    @Override
    public void sendValues(View view) {
        //view.get
        Log.d(TAG, "sendValues: "+view.toString());

        //getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("fragment_tag_one1")).commit();
    }
    public void dataGetter(ArrayList<String> locationlist){
       int no_of_locations=locationlist.size();
        for(int j=0;j<no_of_locations-1;j++){
            for(int k=j+1;k<no_of_locations;k++){
                String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+
                        locationlist.get(j)+ "&destination="+locationlist.get(k)+"&key=AIzaSyApfn0IRy81vEVRPSA1DXZUqGO_27vcXNE";
                Log.d(TAG, "dataGetter: "+url);
               // new GetData(MainActivity.this).execute(url);

            }
        }
    }
}

//For Auto Completion

 /*PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        }); */
