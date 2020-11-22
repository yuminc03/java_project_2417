package kr.hs.emirim.flowerbeen.byeruss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;

public class MyDBHandler {

    private final String TAG = "MyDBHandler";

    MySQLiteOpenHelper mySQLiteOpenHelper = null;
    SQLiteDatabase sqLiteDatabase = null;

    public MyDBHandler(Context context) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }

//    public static MyDBHandler open(Context context, String name) {
//        return new MyDBHandler(context, name);
//    }

    public Cursor select()
    {
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.query("byeruss_make_room", null, null, null, null, null, null);
        return c;
    }

    public void insert(String roomName, String roomTime, String roomPlace) {

        Log.d(TAG, "insert");

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("roomName", roomName);
        value.put("roomTime", roomTime);
        value.put("roomPlace", roomPlace);

        sqLiteDatabase.insert("byeruss_make_room", null, value);

    }

    public void delete(String name)
    {
        Log.d(TAG, "delete");
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.delete("byeruss_make_room", "roomName=?", new String[]{name});
    }

    public void close() {
        mySQLiteOpenHelper.close();
    }

}
