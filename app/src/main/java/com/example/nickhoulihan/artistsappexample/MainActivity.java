package com.example.nickhoulihan.artistsappexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper db = new dbHelper(this);
        String logTag = "Artists";

        /**
         * CRUD Operations
         * */

        // Inserting Contacts
        Log.d(logTag, "Inserting ..");
        db.addArtists(new Artists(1, "Chris Brown", "R&B Artists"));
        db.addArtists(new Artists(2, "Chris Brown", "R&B Artists"));
        db.addArtists(new Artists(3, "Chris Brown", "R&B Artists"));
        db.addArtists(new Artists(4, "Chris Brown", "R&B Artists"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Artists> artists = db.getAllArtists();
        for (Artists a : artists) {
            String log = "Id: "+a.getId()+" ,Name: " + a.getName() + " ,Phone: " + a.getDescription();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}
