package com.example.admin.vybor.util;

import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.vybor.Models.datatypes.FactionVotesData;
import com.example.admin.vybor.Models.datatypes.LawData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "VYBOR";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;

        try {
            this.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getFilesDir().getPath() + '/' + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database does't exist yet.
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = myContext.getFilesDir().getPath() + '/' + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = myContext.getFilesDir().getPath() + '/' + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private Cursor execSQL(String SQL) {
        Cursor c = myDataBase.rawQuery(SQL, null);
        return c != null && c.moveToFirst() ? c : null;
    }

    public void checkLawsData() {
        this.openDataBase();

        Cursor c = this.execSQL("SELECT * FROM laws");
        if (c != null) {
            String s = c.getString(c.getColumnIndex("informal_name"));
            System.out.println("********************************************" + s);
        }

        this.close();
    }

    public String[] getDeputyData() {

        this.openDataBase();

        ArrayList<String> data = new ArrayList<>();

        Cursor cursor = this.execSQL("SELECT * FROM deputy");
        if (cursor != null) {

            while (cursor.isAfterLast() == false) {
                String s = cursor.getString(cursor.getColumnIndex("deputy"));
                data.add(s);
                cursor.moveToNext();
            }

        }

        this.close();

        String[] tmpArrat = new String[data.size()];
        tmpArrat = data.toArray(tmpArrat);
        return tmpArrat;
    }


    public List<LawData> getLawsData() {

        this.openDataBase();

        List<LawData> data = new ArrayList<>();

        Cursor cursor = this.execSQL("SELECT informal_name,official_name,__id as id FROM laws");
        if (cursor != null) {
            while (cursor.isAfterLast() == false) {
                String informal_name = cursor.getString(cursor.getColumnIndex("informal_name"));
                String official_name = cursor.getString(cursor.getColumnIndex("official_name"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                data.add(new LawData(informal_name, official_name, id));
                cursor.moveToNext();
            }
        }

        this.close();

        return data;
    }

    public List<FactionVotesData> getFactionsVotes() {

        this.openDataBase();

        List<FactionVotesData> data = new ArrayList<>();

        Cursor cursor = this.execSQL("SELECT * FROM votes_percent");
        if (cursor != null) {
            while (cursor.isAfterLast() == false) {
                String faction = cursor.getString(cursor.getColumnIndex("faction"));
                Integer vote = cursor.getInt(cursor.getColumnIndex("vote"));
                Integer percent = Math.round(cursor.getFloat(cursor.getColumnIndex("percent")));
                Integer id = Math.round(cursor.getFloat(cursor.getColumnIndex("vote_id")));
                data.add(new FactionVotesData(faction, vote, percent, id));
                cursor.moveToNext();
            }
        }

        this.close();

        return data;
    }

//    public List<FactionVotesData> getFactionsVotesForLaw(Integer lawId) {
//
//        this.openDataBase();
//
//        List<FactionVotesData> data = new ArrayList<>();
//
//        Cursor cursor = this.execSQL("SELECT * FROM votes_percent WHERE vote_id =" + lawId);
//        if (cursor != null) {
//            while (cursor.isAfterLast() == false) {
//                String faction = cursor.getString(cursor.getColumnIndex("faction"));
//                Integer vote = cursor.getInt(cursor.getColumnIndex("vote"));
//                Integer percent = Math.round(cursor.getFloat(cursor.getColumnIndex("percent")));
//                data.add(new FactionVotesData(faction, vote, percent));
//                cursor.moveToNext();
//            }
//        }
//
//        this.close();
//
//        return data;
//    }
//

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}
