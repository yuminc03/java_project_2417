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

    MySQLiteOpenHelper mHelper = null;
    SQLiteDatabase mDB = null;

    public MyDBHandler(Context context, String name) {
        mHelper = new MySQLiteOpenHelper(context, name, null, 1);
    }

    public static MyDBHandler open(Context context, String name) {
        return new MyDBHandler(context, name);
    }

    public Cursor select()
    {
        mDB = mHelper.getReadableDatabase();
        Cursor c = mDB.query("byeruss_make_room", null, null, null, null, null, null);
        return c;
    }

    public void insert(String roomName, int roomTime, String roomPlace) {

        Log.d(TAG, "insert");

        mDB = mHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("roomName", roomName);
        value.put("roomTime", roomTime);
        value.put("roomPlace", roomPlace);

        mDB.insert("byeruss_make_room", null, value);

    }

    public void delete(String name)
    {
        Log.d(TAG, "delete");
        mDB = mHelper.getWritableDatabase();
        mDB.delete("byeruss_make_room", "roomName=?", new String[]{name});
    }

    public void close() {
        mHelper.close();
    }


}
