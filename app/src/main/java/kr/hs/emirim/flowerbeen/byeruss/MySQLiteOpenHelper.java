package kr.hs.emirim.flowerbeen.byeruss;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = "MySQLiteOpenHelper";
    String tableName = "byeruss_make_room";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//사용중인 DB가 없을 때 호출

        String sql = "CREATE TABLE tableName (roomId INTEGER NOT NULL PRIMARY KEY, roomName TEXT NOT NULL, roomTime TEXT NOT NULL, roomPlace TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//사용중인 코드의 버전이 바뀐 경우 호출
        // 1 -> 2 로 바뀐 경우
        String sql="DROP TABLE IF EXISTS tableName";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}