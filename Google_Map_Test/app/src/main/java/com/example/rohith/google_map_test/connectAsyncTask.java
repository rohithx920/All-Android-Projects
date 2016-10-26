package com.example.rohith.google_map_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohith on 7/14/2016.
 */
public class connectAsyncTask extends AsyncTask<String, Void, String> {
    private ProgressDialog progressDialog;
    String url;
    Activity mActivity;
    connectAsyncTask(MapsActivity urlPass){
        mActivity = urlPass;
    }
    /*@Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Fetching route, Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    } */

    @Override
    protected String doInBackground(String... strings) {
        if(strings!=null){
            drawPath(strings[0]);
        }

        return strings[0];
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //progressDialog.hide();

    }
    void drawPath(String string){
        List<LatLng> list = decodePoly(MapsActivity.map.getPolyline());
        Polyline line = MapsActivity.mMap.addPolyline(new PolylineOptions()
                .addAll(list)
                .width(12)
                .color(Color.parseColor("#05b1fb"))//Google maps blue color
                .geodesic(true)
        );
        Toast.makeText(mActivity, "in draw path", Toast.LENGTH_SHORT).show();
    }
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }
}