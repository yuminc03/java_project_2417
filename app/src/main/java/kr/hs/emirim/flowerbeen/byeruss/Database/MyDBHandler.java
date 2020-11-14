package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class MyDBHandler {

    private final String TAG = "MyDBHandler";

    MySQLiteOpenHelper mySQLiteOpenHelper = null;
    SQLiteDatabase sqLiteDatabase = null;

    public MyDBHandler(Context context, String name) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context, name, null, 1);
    }

    public static MyDBHandler open(Context context, String name) {
        return new MyDBHandler(context, name);
    }

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
