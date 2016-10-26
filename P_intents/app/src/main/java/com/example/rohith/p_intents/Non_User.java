package com.example.rohith.p_intents;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rohith on 6/8/2016.
 */
public class Non_User implements Parcelable{
    String name;
    String email;

    public Non_User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);

    }
    public static final Parcelable.Creator<Non_User> CREATOR
            = new Parcelable.Creator<Non_User>() {
        public Non_User createFromParcel(Parcel in) {
            return new Non_User(in);
        }

        public Non_User[] newArray(int size) {
            return new Non_User[size];
        }
    };

    private Non_User(Parcel in) {
        this.name=in.readString();
        this.email=in.readString();
    }
}
