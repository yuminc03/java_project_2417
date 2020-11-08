package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class MyDBHandler {

    private Context context;

    /*
    private final String TAG = "MyDBHandler";
    SQLiteOpenHelper mHelper = null;
    SQLiteDatabase mDB = null;
    String tableName = "byeruss_make_room";
     */

    public MyDBHandler(Context context){//생성자
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public int insertRoom(RoomItem roomItem){//방에 데이터 삽입
        int id = -1;
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(Config.COLUMN_ROOM_ID, roomItem.getRoomId());
        contentValues.put(Config.COLUMN_ROOM_NAME, roomItem.getRoomName());
        contentValues.put(Config.COLUMN_ROOM_TIME, roomItem.getRoomTime());
        contentValues.put(Config.COLUMN_ROOM_PLACE, roomItem.getRoomPlace());

        try{
            id = (int) sqLiteDatabase.insertOrThrow(Config.ROOM_TABLE_NAME, null, contentValues);
        }catch(SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "작업을 실패했습니다: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }finally{
            sqLiteDatabase.close();
        }

        return id;
    }

    public List<RoomItem> getAllRoomItem(){//모든 RoomItem을 얻어온다

        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(Config.ROOM_TABLE_NAME, null, null, null, null, null, null, null);

            /**
             //raw query를 실행하려면 두 줄 아래의 주석 처리를 제거하십시오. 그리고 줄 위에 주석 처리하십시오.
             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<RoomItem> roomList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ROOM_ID));
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_NAME));
                        String time = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_TIME));
                        String place = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_PLACE));

                        roomList.add(new RoomItem(name, time, place));
                    }   while (cursor.moveToNext());

                    return roomList;
                }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "작업을 실패했습니다", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

    public RoomItem getRoomById(int roomid){//아이디를 얻어온다

        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        RoomItem roomItem = null;
        try {

            cursor = sqLiteDatabase.query(Config.ROOM_TABLE_NAME, null,
                    Config.COLUMN_ROOM_ID + " = ? ", new String[]{String.valueOf(roomid)},
                    null, null, null);

            /*
             //raw query를 실행하려면 두 줄 아래의 주석 처리를 제거하십시오. 그리고 위의 sqLiteDatabase.query() 메서드를 주석 처리한다
             String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ROOM_ID));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_NAME));
                String time = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_TIME));
                String place = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ROOM_PLACE));

                roomItem = new RoomItem(name, time, place);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "작업을 실패했습니다", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return roomItem;
    }

    public int updateRoomInfo(RoomItem roomItem){//방의 데이터를 업데이트 한다

        int rowCount = 0;
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(Config.COLUMN_ROOM_ID, roomItem.getRoomId());
        contentValues.put(Config.COLUMN_ROOM_NAME, roomItem.getRoomName());
        contentValues.put(Config.COLUMN_ROOM_TIME, roomItem.getRoomTime());
        contentValues.put(Config.COLUMN_ROOM_PLACE, roomItem.getRoomPlace());

        try {
            rowCount = sqLiteDatabase.update(Config.ROOM_TABLE_NAME, contentValues,
                    Config.COLUMN_ROOM_ID + " = ? ",
                    new String[] {String.valueOf(roomItem.getRoomId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public int deleteRoomById(int id) {//아이디를 이용하여 하나의 데이터를 삭제
        int deletedRowCount = -1;
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.ROOM_TABLE_NAME,
                    Config.COLUMN_ROOM_ID + " = ? ",
                    new String[]{ String.valueOf(id)});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public boolean deleteAllRooms(){//모든 방 삭제
        boolean deleteStatus = false;
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        try {
            //"1"의 경우 delete () 메서드는 삭제 된 행 수를 반환합니다
            //행 수를 원하지 않으면 delete (TABLE_NAME, null, null)를 사용하십시오
            sqLiteDatabase.delete(Config.ROOM_TABLE_NAME, null, null);

            int count = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.ROOM_TABLE_NAME);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

    public int getNumberOfRoom(){//방 개수를 얻는다
        int count = -1;
        MySQLiteOpenHelper mySQLiteOpenHelper = MySQLiteOpenHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        try {
            count = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.ROOM_TABLE_NAME);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    //members

    /*
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
     */

}
