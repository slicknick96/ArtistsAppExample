package com.example.nickhoulihan.artistsappexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "Artists_DB";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String TABLE_ARTISTS = "artistsTable";

    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table artistsTable (id Integer primary key, name text, description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTISTS);
        onCreate(db);
    }

    //  CRUD
    public void addArtists(Artists artists) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, artists.getName());
        values.put(KEY_DESCRIPTION, artists.getDescription());
        // Inserting Row
        db.insert(TABLE_ARTISTS, null, values);
        db.close();
    }

    public Artists getArtists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ARTISTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Artists artists = new Artists(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return artists;
    }

    public List<Artists> getAllArtists() {
        List<Artists> artistsList = new ArrayList<Artists>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ARTISTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Artists artists = new Artists();
                artists.setId(Integer.parseInt(cursor.getString(0)));
                artists.setName(cursor.getString(1));
                artists.setDescription(cursor.getString(2));
                // Adding artist to list
                artistsList.add(artists);
            } while (cursor.moveToNext());
        }
        // return artist list
        return artistsList;
    }

    public int getArtistsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ARTISTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    public int updateContact(Artists artists) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, artists.getName());
        values.put(KEY_DESCRIPTION, artists.getDescription());

        // updating row
        return db.update(TABLE_ARTISTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(artists.getId()) });
    }

    public void deleteContact(Artists artists) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ARTISTS, KEY_ID + " = ?",
                new String[] { String.valueOf(artists.getId()) });
        db.close();
    }
}
