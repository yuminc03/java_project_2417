package kr.hs.emirim.flowerbeen.byeruss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler {

    private final String TAG = "MyDBHandler";

    SQLiteOpenHelper mHelper = null;
    SQLiteDatabase mDB = null;
    String tableName = "byeruss_make_room";

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

    public void insert(String roomId, String roomName, String roomTime, String roomPlace) {

        Log.d(TAG, "insert");

        mDB = mHelper.getWritableDatabase();

        // 아이디 기준으로 중복 체크
        String query = "select idx from " + tableName
                + " where " + roomId + "= '"+ roomId + "'";
        Cursor cursor = mDB.rawQuery(query, null);
        cursor.moveToFirst(); // Cursor를 제일 첫행으로 이동
        if( cursor.getCount() == 0) {  // 중복이 없으면 저장하라.
            ContentValues cv = new ContentValues(); // 객체 생성
            cv.put(roomId, roomId);
            cv.put(roomName, roomName);
            cv.put(roomTime, roomTime);
            cv.put(roomPlace, roomPlace);
            mDB.beginTransaction();  // 대량건수 데이터 입력 처리를 고려
            try{
                long rowId = mDB.insert(tableName, null, cv);
                if(rowId < 0){
                    throw new SQLException("Fail to Insert");
                }
                mDB.setTransactionSuccessful();
            } catch(Exception e){
                Log.i(TAG,e.toString());
            } finally {
                mDB.endTransaction();
                Log.v(TAG,"DB Inserted " + roomId);
            }
        }
        cursor.close();
        mDB.close();

        ContentValues value = new ContentValues();
        value.put("roomId", roomId);
        value.put("roomName", roomName);
        value.put("roomTime", roomTime);
        value.put("roomPlace", roomPlace);

        mDB.insert("byeruss_make_room", null, value);

    }

    public void delete(String roomId)
    {
        Log.d(TAG, "delete");
        mDB = mHelper.getWritableDatabase();
        mDB.delete("byeruss_make_room", "roomId=?", new String[]{roomId});
    }

    public void close() {
        mHelper.close();
    }

}
