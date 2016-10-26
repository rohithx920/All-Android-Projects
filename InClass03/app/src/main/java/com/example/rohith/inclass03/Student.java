package com.example.rohith.inclass03;

/**
 * Created by Rohith on 5/31/2016.
 */
        import android.os.Parcel;
        import android.os.Parcelable;
        import android.widget.RadioButton;

/**
 * Created by This on 5/31/16.
 */
public class Student implements Parcelable {

    public String name;
    public String emailAddress;
    public String fvprog;
    //private RadioButton prog_lang;
    public Student(String name,String emailAddress,String fvprog) {
        this.name = name;
        this.emailAddress=emailAddress;
        this.fvprog=fvprog;
    }


    protected Student(Parcel in) {
        name = in.readString();
        emailAddress = in.readString();
        fvprog=in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", fvprog='" + fvprog + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(emailAddress);
        dest.writeString(fvprog);

    }
}