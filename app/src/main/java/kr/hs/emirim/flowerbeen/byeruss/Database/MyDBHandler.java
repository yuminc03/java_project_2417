package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import kr.hs.emirim.flowerbeen.byeruss.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class MyDBHandler {

    private Context context;

    private final String TAG = "MyDBHandler";
    SQLiteOpenHelper mHelper = null;
    SQLiteDatabase mDB = null;
    String tableName = "byeruss_make_room";

    public MyDBHandler(Context context){
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public long insertRoom(RoomItem roomItem){
        long id = -1;
        
        return id;
    }
    public MyDBHandler(Context context, String name) {
        mHelper = new MySQLiteOpenHelper(context, name, null, 1);
    }

    public static MyDBHandler open(Context context, String name) {
        return new MyDBHandler(context, name);
    }

    public Cursor select() {
        mDB = mHelper.getReadableDatabase();
        Cursor c = mDB.query(tableName, null, null, null, null, null, null);
        return c;
    }

    public void insert(String roomName, String roomTime, String roomPlace) {

        Log.d(TAG, "insert");

        mDB = mHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("roomName", roomName);
        value.put("roomTime", roomTime);
        value.put("roomPlace", roomPlace);

        mDB.insert(tableName, null, value);
    }

    public void delete(String roomName)
    {
        Log.d(TAG, "delete");
        mDB = mHelper.getWritableDatabase();
        mDB.delete(tableName, "roomName=?", new String[]{roomName});
    }

    public void close() {
        mHelper.close();
    }

}
