package com.example.rohith.p_database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static DataManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);

        Note note = new Note();
        note.setSubject("Note 1");
        note.setText("This is the text for note 1");
        dm.saveNote(note);

        note = new Note();
        note.setSubject("Note 2");
        note.setText("This is the text for note 2");
        dm.saveNote(note);

        note = new Note();
        note.setSubject("Note 3");
        note.setText("This is the text for note 3");
        dm.saveNote(note);
        List<Note> notes = dm.getAllNotes();
        ListView myListView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, notes);
        myListView.setAdapter(adapter);
        note =new Note();

    }
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }
}
