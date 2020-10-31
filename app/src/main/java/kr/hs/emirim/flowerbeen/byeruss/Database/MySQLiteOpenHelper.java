package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static MySQLiteOpenHelper mySQLiteOpenHelper;

    private final String TAG = "MySQLiteOpenHelper";

    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    //constructor
    public MySQLiteOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//사용중인 DB가 없을 때 호출

        String CREATE_ROOM_TABLE = "CREATE TABLE " +  Config.ROOM_TABLE_NAME + "("
                + Config.COLUMN_ROOM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_ROOM_NAME +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_TIME +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_PLACE +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_MEMBER +" TEXT NOT NULL "
                + ")";

        String CREATE_MEMBER_TABLE = "CREATE TABLE " +  Config.MEMBER_TABLE_NAME + "("
                + Config.COLUMN_MEMBER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_MEMBER_1 + " TEXT, "
                + Config.COLUMN_MEMBER_2 + " TEXT, "
                + Config.COLUMN_MEMBER_3 + " TEXT, "
                + Config.COLUMN_MEMBER_4 + " TEXT, "
                + Config.COLUMN_MEMBER_5 + " TEXT, "
                + Config.COLUMN_MEMBER_6 + " TEXT "
                + ")";
        sqLiteDatabase.execSQL(CREATE_ROOM_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//사용중인 코드의 버전이 바뀐 경우 호출

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.ROOM_TABLE_NAME);
    }
}