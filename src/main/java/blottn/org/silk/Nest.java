package blottn.org.silk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Nick on 20/10/2017.
 * Class for storing data captured by the web
 */

public class Nest extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WebDatabase.db";


    public static class WebDatabaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";

        public static final String COLUMN_NAME_INSTANT = "instant";     //placeholder basic information
        public static final String COLUMN_NAME_TYPE = "type";
    }

    // SQL command to create the table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WebDatabaseEntry.TABLE_NAME + " (" +
                    WebDatabaseEntry._ID + " INTEGER PRIMARY KEY," +
                    WebDatabaseEntry.COLUMN_NAME_INSTANT + " BIGINT," +
                    WebDatabaseEntry.COLUMN_NAME_TYPE + " TEXT)";

    // SQL command to delete the table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WebDatabaseEntry.TABLE_NAME;



    public Nest(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onUpgrade(db,oldVersion,newVersion);
    }

    public void insert(Prey prey) {
        System.out.println("DB: inserted an item");
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WebDatabaseEntry.COLUMN_NAME_INSTANT,prey.getInstant());
        values.put(WebDatabaseEntry.COLUMN_NAME_TYPE,"wowow");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(WebDatabaseEntry.TABLE_NAME, null, values);
    }

    public Cursor query(long time, String type) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                WebDatabaseEntry._ID,
                WebDatabaseEntry.COLUMN_NAME_INSTANT,
                WebDatabaseEntry.COLUMN_NAME_TYPE
        };

        String selection = WebDatabaseEntry.COLUMN_NAME_TYPE + " = ?";
        String[] selectionArgs = {
                type
        };

        String sortOrder = WebDatabaseEntry.COLUMN_NAME_INSTANT + " ASC";

        Cursor cursor = db.query(
                WebDatabaseEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        return cursor;
    }

}
