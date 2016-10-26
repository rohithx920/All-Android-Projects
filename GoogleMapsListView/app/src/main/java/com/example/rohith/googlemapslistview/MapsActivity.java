package com.example.rohith.googlemapslistview;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    static GoogleMap mMap;
    ArrayList<Map> mylist;
    static Map map;
    static MapsActivity mapsActivity;
    String source,destination,d_lat,d_lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapsActivity=this;
        mylist= (ArrayList<Map>) getIntent().getExtras().getSerializable("sending_vlaues");
        map=mylist.get(0);
        //optimizedPathFinder();
        Log.d("888", "onCreate: "+map);
        source= map.getSource();
        destination= map.getDest();
        d_lat=map.getD_lat();
        d_lng=map.getD_long();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String polyline = null;
        //new connectAsyncTask(MapsActivity.this).execute(map.getPolyline());
        // Add a marker in Source and move the camera

        int size= mylist.size();

        //float smallest=
        for(int l=0;l<size;l++) {
            Map map1 = mylist.get(l);
            Log.d("777777", ""+map1.getSource()+","+map1.getDest()+","+map1.getDist());


            LatLng src_lat_lng = new LatLng(Double.parseDouble(map1.getS_lat()), Double.parseDouble(map1.getS_long()));
            mMap.addMarker(new MarkerOptions().position(src_lat_lng).title(map1.getSource().toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(src_lat_lng));


            // Add a marker in Dest and move the camera

            LatLng dest_lat_lng = new LatLng(Double.parseDouble(map1.getD_lat()), Double.parseDouble(map1.getD_long()));
            mMap.addMarker(new MarkerOptions().position(dest_lat_lng).title(map1.getDest().toString()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(dest_lat_lng));

            mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(Double.parseDouble(map1.getS_lat()), Double.parseDouble(map1.getS_long())),
                            new LatLng(Double.parseDouble(map1.getD_lat()),Double.parseDouble(map1.getD_long())))
                    .width(10)
                    .color(Color.BLUE).clickable(true));
            //Object onPolylineClickListener=null;
            //GoogleMap.OnPolylineClickListener


            //drawing a path between source and distination
            /*
            if(polyline==null)
            polyline = map1.getPolyline();
            else
            polyline=polyline.concat(map1.getPolyline().toString());
            Log.d("7777", ""+polyline.length());
            Log.d("7777", ""+map1.getPolyline());
            */
        }

        //drawPath(polyline);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MapsActivity.this, "Turn on Gps", Toast.LENGTH_SHORT).show();
            Log.d("TAG", "onMapReady: ");
            return;
        }
        mMap.setMyLocationEnabled(true);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void optimizedPathFinder(){
        int size= mylist.size();
        int temp=0;
        int no_of_locations=getIntent().getExtras().getInt("Total_locations");
        //float smallest=
        HashMap<String, Double> mymap=new HashMap<String, Double>();
        //int no
        ArrayList<Vertex> vertexArrayList=new ArrayList<Vertex>();
        while(temp<size) {
            Map map2 = mylist.get(temp);
            Vertex vertex = new Vertex();
            vertex.mymap = new HashMap<String, Edge>();
            vertex.source = map2.getSource();
            int q=temp+no_of_locations-1;
            for (int l = temp; l < q; l++) {
                Edge edge = new Edge();
                edge.source = mylist.get(l).getSource();
                edge.destination = mylist.get(l).getDest();
                edge.distance = Double.parseDouble(mylist.get(l).getDist().substring(0, mylist.get(l).getDist().length() - 3));
                vertex.mymap.put(edge.source+";"+edge.destination, edge);
            }
            vertexArrayList.add(vertex);
            temp=temp+no_of_locations-1;
        }
        Log.d("555678", "optimizedPathFinder: "+vertexArrayList.size());
       // generateGraph(vertexArrayList);

    }
    public void generateGraph(ArrayList<Vertex> vertexArrayList){
    int arraylistsize=vertexArrayList.size();
    int start=0,start2;
        Vertex v=vertexArrayList.get(start);
        String soruce_vertex = v.source;
        int s_dest_size=v.mymap.size();
        for(start2=0;start2<s_dest_size;start2++){
           // v.mymap.get()
        }
    }
public class Vertex{
    String source;
    ArrayList<Edge> source_edges;
    HashMap<String,Edge> mymap;
}
public class Edge{
    String source;
    String destination;
    double distance;
    String distacne_measurement;
}

}

