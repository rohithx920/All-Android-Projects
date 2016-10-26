package com.example.rohith.googlemapslistview;


import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rohith on 7/13/2016.
 */
public class MapUtil {
    //static ArrayList<Map> weather_list = new ArrayList<Map>();
    static public class PersonJsonParser {
        static Map parseStudent(String in) throws JSONException {
            Log.d("invalue", "parseStudent: " + in);
            Log.d("uuuu", "parseStudent: ");
            JSONObject root = new JSONObject(in);
            Log.d("uuuu", "parseStudent: " + root);


            JSONArray weather_jsonArray = root.getJSONArray("routes");
            if (weather_jsonArray.length() != 0) {
                Log.d("length", "parseStudent: " + weather_jsonArray.length());

                JSONObject obj = (JSONObject) weather_jsonArray.get(0);
                Log.d("objjjj", "parseStudent: " + obj);
                Map weather = new Map();
                JSONArray weather_jsonArray2 = obj.getJSONArray("legs");
                JSONObject obj2 = (JSONObject) weather_jsonArray2.get(0);
                JSONObject main_obj = (JSONObject) obj2.get("distance");
                JSONObject main_obj2 = (JSONObject) obj2.get("duration");
                JSONObject main_obj3 = (JSONObject) obj2.get("end_location");
                JSONObject main_obj4 = (JSONObject) obj2.get("start_location");
                Log.d("objjjj333", "" + main_obj);
                Log.d(GetData.TAG, "Main" + main_obj.toString());
                weather.setDist(main_obj.getString("text"));
                weather.setDur(main_obj2.getString("text"));
                weather.setSource(obj2.getString("start_address"));
                weather.setDest(obj2.getString("end_address"));
                weather.setS_lat(main_obj4.getString("lat"));
                weather.setS_long(main_obj4.getString("lng"));
                weather.setD_lat(main_obj3.getString("lat"));
                weather.setD_long(main_obj3.getString("lng"));
                JSONObject main_obj5 = (JSONObject) obj.get("overview_polyline");

                weather.setPolyline(main_obj5.getString("points"));


                //weather.set(main_obj.getDouble("value"));
                //weather.setPressure(main_obj.getDouble("pressure"));
                //weather.setIcon(main_obj.getString("ic"));
                //Log.d("test",""+main_obj.getString("text"));
            /*
            // JSONObject weather_obj= (JSONObject) obj.get("weather");
            JSONArray weather_obj_jsonArray=obj.getJSONArray("weather");
            JSONObject des_image_object= (JSONObject) weather_obj_jsonArray.get(0);
            Log.d("ppppp", "parseStudent: "+des_image_object.length());
            weather.setDescription(des_image_object.getString("description"));
            weather.setIcon(des_image_object.getString("icon"));*/
                //weather_list.add(weather);
                Log.d("777", "" + weather.getSource() + "," + weather.getDest() + "" + weather.getDist());
                return weather;
            }
            else{
                //Toast.makeText(MainActivity.mainActivity, "Not in Same country", Toast.LENGTH_SHORT).show();
                return  null;
            }
        }
    }

}
