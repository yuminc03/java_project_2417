package kr.hs.emirim.flowerbeen.byeruss.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static MySQLiteOpenHelper mySQLiteOpenHelper;

    //all static variables
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    //constructor
    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static synchronized MySQLiteOpenHelper getInstance(Context context){
        if(mySQLiteOpenHelper == null){
            mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        }
        return mySQLiteOpenHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//사용중인 DB가 없을 때 호출

        String CREATE_ROOM_TABLE = "CREATE TABLE " +  Config.ROOM_TABLE_NAME + "("
                + Config.COLUMN_ROOM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_ROOM_NAME +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_TIME +" TEXT NOT NULL, "
                + Config.COLUMN_ROOM_PLACE +" TEXT NOT NULL "
                + ")";

        String CREATE_MEMBER_TABLE = "CREATE TABLE " +  Config.MEMBER_TABLE_NAME + "("
                + Config.COLUMN_MEMBER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_MEMBER_1 + " TEXT, "
                + Config.COLUMN_MEMBER_2 + " TEXT, "
                + Config.COLUMN_MEMBER_3 + " TEXT, "
                + Config.COLUMN_MEMBER_4 + " TEXT, "
                + Config.COLUMN_MEMBER_5 + " TEXT, "
                + Config.COLUMN_MEMBER_6 + " TEXT, "
                + "FOREIGN KEY (" + Config.COLUMN_MEMBER_ID + ") REFERENCES " + Config.ROOM_TABLE_NAME + "(" + Config.COLUMN_MEMBER_ID + ") ON UPDATE CASCADE ON DELETE CASCADE"
                + ")";
        sqLiteDatabase.execSQL(CREATE_ROOM_TABLE);
        sqLiteDatabase.execSQL(CREATE_MEMBER_TABLE);

        Logger.d("DB created! ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {//사용중인 코드의 버전이 바뀐 경우 호출

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.ROOM_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Config.MEMBER_TABLE_NAME);

        //craete tables again
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {//데이터베이스가 열렸을 때 호출
        super.onOpen(sqLiteDatabase);

        //ON UPDATE CASCADE, ON DELETE CASCADE와 같은 외래키 제약 조건 사용
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        //table_room = 외래 키가 참조하는 테이블 인 상위 테이블
        //table_member = 외래 키 제약 조건이 적용되는 하위 테이블
        //상위 및 하위 테이블에있는 행 간의 관계를 적용하려면 외래 키 제약 조건 을 사용
        //상위테이블에서 해당 행을 삭제하거나 업데이트하지 않고 테이블 에서 행을 제거 할 수 있다
    }
}