package sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pictureManagerDB";

    // Contacts table name
    private static final String TABLE_CONTACTS = "picturesTable";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ID_STATION = "idStation";
    private static final String KEY_NAME = "nomStation";
    private static final String KEY_DATE = "dateCreation";
    private static final String KEY_TITRE = "titre";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_TITRE + " TEXT," + KEY_ID_STATION + " INTEGER,"
                + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addPicture(PictureStation picture) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, picture.getNomStation());
        values.put(KEY_ID_STATION, picture.getIdStation());
        values.put(KEY_TITRE, picture.getTitre());

        values.put(KEY_DATE, picture.getDateCreation()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact


    // Getting All Contacts
    public List<PictureStation> getAll() {
        List<PictureStation> contactList = new ArrayList<PictureStation>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PictureStation contact = new PictureStation();
                contact.setId(Integer.parseInt(cursor.getString(0))); //id

                contact.setNomStation(cursor.getString(1));//name
                contact.setTitre(cursor.getString(2));//titre
                contact.setIdStation(Integer.parseInt(cursor.getString(3)));//id sattion
                contact.setDateCreation(cursor.getString(4));//date
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(PictureStation picture) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, picture.getNomStation());
        values.put(KEY_DATE, picture.getDateCreation());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(picture.getId())});
    }

    // Deleting single contact
    public void deleteContact(PictureStation picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(picture.getId())});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
