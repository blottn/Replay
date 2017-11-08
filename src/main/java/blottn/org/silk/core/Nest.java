package blottn.org.silk.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 20/10/2017.
 * Class for storing data captured by the web
 */

public final class Nest extends SQLiteOpenHelper {

    final Map<Integer, String> ACTION_MAPPER;


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
        ACTION_MAPPER = new HashMap<Integer, String>();
        initMapper();
    }

    private void initMapper() {

        //By doing it like this I don't have to rewrite the code each time google updates the api...
        ACTION_MAPPER.put(MotionEvent.ACTION_BUTTON_PRESS, "ACTION_BUTTON_PRESS");
        ACTION_MAPPER.put(MotionEvent.ACTION_BUTTON_RELEASE, "ACTION_BUTTON_RELEASE");
        ACTION_MAPPER.put(MotionEvent.ACTION_CANCEL,"ACTION_CANCEL");
        ACTION_MAPPER.put(MotionEvent.ACTION_DOWN,"ACTION_DOWN");
        ACTION_MAPPER.put(MotionEvent.ACTION_UP,"ACTION_UP");
        ACTION_MAPPER.put(MotionEvent.ACTION_HOVER_ENTER,"ACTION_HOVER_ENTER");
        ACTION_MAPPER.put(MotionEvent.ACTION_HOVER_EXIT,"ACTION_HOVER_EXIT");
        ACTION_MAPPER.put(MotionEvent.ACTION_HOVER_MOVE,"ACTION_HOVER_MOVE");
        ACTION_MAPPER.put(MotionEvent.ACTION_MOVE,"ACTION_MOVE");
        ACTION_MAPPER.put(MotionEvent.ACTION_OUTSIDE,"ACTION_OUTSIDE");
        ACTION_MAPPER.put(MotionEvent.ACTION_POINTER_DOWN,"ACTION_POINTER_DOWN");
        ACTION_MAPPER.put(MotionEvent.ACTION_POINTER_UP,"ACTION_POINTER_UP");

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
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(WebDatabaseEntry.COLUMN_NAME_INSTANT,prey.getInstant());
        values.put(WebDatabaseEntry.COLUMN_NAME_TYPE,ACTION_MAPPER.get(prey.getEvent().getAction()));
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

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from " + WebDatabaseEntry.TABLE_NAME,null);
    }
}
