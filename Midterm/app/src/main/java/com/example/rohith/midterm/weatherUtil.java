package com.example.rohith.midterm;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rohith on 6/9/2016.
 */
public class weatherUtil {
    static public class PersonJsonParser {
        static ArrayList<weather> parseStudent(String in) throws JSONException {
            Log.d("invalue", "parseStudent: "+in);
            ArrayList<weather> weather_list = new ArrayList<weather>();
            Log.d("uuuu", "parseStudent: ");
            JSONObject root=new JSONObject(in);
            Log.d("uuuu", "parseStudent: ");
            JSONArray weather_jsonArray=root.getJSONArray("list");
            Log.d("length", "parseStudent: "+weather_jsonArray.length());

                JSONObject obj= (JSONObject) weather_jsonArray.get(0);
            Log.d("objjjj", "parseStudent: "+obj);
            weather weather=new weather();
                JSONObject main_obj= (JSONObject) obj.get("main");
            Log.d("objjjj333", ""+main_obj);
            Log.d(GetData.TAG, "Main"+main_obj.toString());
                weather.setHumidity(main_obj.getDouble("humidity"));
                weather.setTemperature(main_obj.getDouble("temp"));
                weather.setPressure(main_obj.getDouble("pressure"));
                //weather.setIcon(main_obj.getString("ic"));
                Log.d("test",""+main_obj.getInt("humidity"));

           // JSONObject weather_obj= (JSONObject) obj.get("weather");
            JSONArray weather_obj_jsonArray=obj.getJSONArray("weather");
            JSONObject des_image_object= (JSONObject) weather_obj_jsonArray.get(0);
            Log.d("ppppp", "parseStudent: "+des_image_object.length());
            weather.setDescription(des_image_object.getString("description"));
            weather.setIcon(des_image_object.getString("icon"));
            weather_list.add(weather);
            return weather_list;
        }
    }

}
