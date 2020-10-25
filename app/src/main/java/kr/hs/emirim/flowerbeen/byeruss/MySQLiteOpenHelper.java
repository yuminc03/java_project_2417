package kr.hs.emirim.flowerbeen.byeruss;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.DriverManager.println;

class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = "MySQLiteOpenHelper";
    String tableName = "byeruss_make_room";

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//사용중인 DB가 없을 때 호출
        if(sqLiteDatabase != null){
            String sql = "CREATE TABLE " +  tableName + "("
            + "roomId INTEGER NOT NULL PRIMARY KEY,"
            + "roomName TEXT NOT NULL,"
            + "roomTime TEXT NOT NULL,"
            + " roomPlace TEXT NOT NULL"
                    + ")";
            sqLiteDatabase.execSQL(sql);
            //println("table created");
        }else{
            //println("open database first");
        }

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//사용중인 코드의 버전이 바뀐 경우 호출
        //println("onUpgrade() called: " + oldVersion + " / " + newVersion);

        // 1 -> 2 로 바뀐 경우
        if (newVersion > 1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
            //println(tableName + " table deleted");
        }
    }
}