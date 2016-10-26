package com.example.rohith.p_json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rohith on 6/9/2016.
 */
public class PersonUtil {

    static public class PersonJsonParser {
        static ArrayList<Student> parseStudent(String in) throws JSONException {
            Log.d("invalue", "parseStudent: "+in);
            ArrayList<Student> student_list = new ArrayList<Student>();
            Log.d("uuuu", "parseStudent: ");
            JSONObject root=new JSONObject(in);
            Log.d("uuuu", "parseStudent: ");
            JSONArray student_jsonArray=root.getJSONArray("persons");
            Log.d("length", "parseStudent: "+student_jsonArray.length());
            for(int i=0;i<student_jsonArray.length();i++){
                JSONObject obj= (JSONObject) student_jsonArray.get(i);
                Student student=new Student();
                student.setName(obj.getString("name"));
                student.setId(obj.getInt("id"));
                student.setAge(obj.getInt("age"));
                student.setDepartment(obj.getString("department"));
                student_list.add(student);

            }
            return student_list;
        }
    }

}
