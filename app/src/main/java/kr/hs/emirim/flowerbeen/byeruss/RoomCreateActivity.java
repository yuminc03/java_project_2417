package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomCreateListener;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList.RoomListActivity;

public class RoomCreateActivity extends AppCompatActivity {

    private String TAG = "RoomCreateActivity";
    private EditText input_room;
    private EditText input_time;
    private EditText input_place;
    private Button btn_create_room;
    private Button btn_cancel_room;

    private String roomName = "";
    private String roomTime = "";
    private String roomPlace = "";

    MyDBHandler myDBHandler;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        input_room = findViewById(R.id.input_room);
        input_time = findViewById(R.id.input_time);
        input_place = findViewById(R.id.input_place);
        btn_create_room = findViewById(R.id.btn_create_room);
        btn_cancel_room = findViewById(R.id.btn_cancel_room);

        btn_create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRoomData();
                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_cancel_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void insertRoomData() {
        Log.d(TAG, "insertRoomData");

        //String room_name, room_time, room_place;

        if( (input_room.getText() != null) && (input_time.getText() != null)
                && (input_place.getText() != null) ) {
            roomName = input_room.getText().toString();
            roomTime = input_time.getText().toString();
            roomPlace = input_place.getText().toString();
        }
        else {
            Toast.makeText(RoomCreateActivity.this, "모든 방의 정보들을 입력하세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        myDBHandler.insert(roomName, roomTime, roomPlace);

        cursor = myDBHandler.select();  // DB 새로 가져오기
        simpleCursorAdapter.changeCursor(cursor); // Adapter에 변경된 Cursor 설정하기
        simpleCursorAdapter.notifyDataSetChanged(); // 업데이트 하기

    }


}