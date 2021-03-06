package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private final String TAG = "MySQLiteOpenHelper";

    private static MySQLiteOpenHelper mySQLiteOpenHelper;

    //all static variables
    private static final int DATABASE_VERSION = 2;

    //database name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    //constructor
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//사용중인 DB가 없을 때 호출

        String CREATE_ROOM_TABLE = "CREATE TABLE " +  Config.ROOM_TABLE_NAME + "("
                + Config.COLUMN_ROOM_NAME +" TEXT NOT NULL PRIMARY KEY, "
                + Config.COLUMN_ROOM_TIME +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_PLACE +" TEXT NOT NULL "
                + ")";

        String CREATE_MEMBER_TABLE = "CREATE TABLE " +  Config.MEMBER_TABLE_NAME + "("
                + Config.COLUMN_MEMBER_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + Config.COLUMN_ROOM_ID + " TEXT NOT NULL, "
                + Config.COLUMN_MEMBER_1 + " TEXT NOT NULL "
                //+ "FOREIGN KEY (" + Config.COLUMN_ROOM_ID + ") REFERENCES " + Config.ROOM_TABLE_NAME + "(" + Config.COLUMN_ROOM_NAME + ") ON UPDATE CASCADE ON DELETE CASCADE"
                + ")";
        sqLiteDatabase.execSQL(CREATE_ROOM_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//사용중인 코드의 버전이 바뀐 경우 호출

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.ROOM_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.MEMBER_TABLE_NAME);

        //craete tables again
        onCreate(sqLiteDatabase);
    }
}