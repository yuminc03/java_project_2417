package kr.hs.emirim.flowerbeen.byeruss.Features.Room;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class RoomCreateActivity extends AppCompatActivity {

    private String TAG = "RoomCreateActivity";
    private EditText input_room;
    private EditText input_time;
    private EditText input_place;
    private Button btn_create_room;
    //private Button btn_cancel_room;
    private Button btn_overlap;

    private int buttonclick = 0;
    private String memberId;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        input_room = findViewById(R.id.input_room);
        input_time = findViewById(R.id.input_time);
        input_place = findViewById(R.id.input_place);
        btn_create_room = findViewById(R.id.btn_create_room);
        //btn_cancel_room = findViewById(R.id.btn_cancel_room);
        btn_overlap = findViewById(R.id.btn_overlap);

        Intent intent = getIntent();
        memberId = intent.getStringExtra("userID");

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);

        btn_create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonclick == 0){
                    Toast.makeText(getApplicationContext(), "모임이름 중복 체크를 해주세요!", Toast.LENGTH_LONG).show();
                }
                insertRoomData();
            }
        });
//        btn_cancel_room.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
        btn_overlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = 0;
                buttonclick = 1;
                try{
                    sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
                    String sql = "SELECT * FROM byeruss_make_room where roomName='"
                            + input_room.getText().toString() + "';";
                    cursor = sqLiteDatabase.rawQuery(sql, null);
                    int checkName;
                    checkName = cursor.getCount();
                    if(checkName == 0){//중복이 아닌 경우
                        check = 0;
                        Toast.makeText(RoomCreateActivity.this, "사용가능한 이름입니다!", Toast.LENGTH_LONG).show();
                    }else {//중복일 경우
                        check = 1;
                        input_room.setText("");
                        Toast.makeText(RoomCreateActivity.this, "다시 입력해주세요!", Toast.LENGTH_LONG).show();
                    }
                    cursor.close();
                    sqLiteDatabase.close();
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
        });

    }

    public void insertRoomData() {
        Log.d(TAG, "insertRoomData");

        if ((input_room.getText() != null) && (input_time.getText() != null)
                && (input_place.getText() != null)) {

            sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT INTO byeruss_make_room VALUES ( '"
                    + input_room.getText().toString() + "' ,'"
                    + input_time.getText().toString() + "' ,'"
                    + input_place.getText().toString() + "');");

            sqLiteDatabase.execSQL("INSERT INTO byeruss_room_member VALUES (null, '"
                    + input_room.getText().toString() + "' ,'"
                    + memberId + "');");// DB에 입력한 값으로 행 추가

            sqLiteDatabase.close();
            Toast.makeText(RoomCreateActivity.this, "데이터가 입력되었습니다!", Toast.LENGTH_SHORT).show();
            input_room.setText("");
            input_time.setText("");
            input_place.setText("");
        } else {
            Toast.makeText(RoomCreateActivity.this, "모든 방의 정보들을 입력하세요!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

}