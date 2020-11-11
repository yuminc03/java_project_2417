package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomCreateListener;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList.RoomListActivity;

public class RoomCreateActivity extends AppCompatActivity {

    private static RoomCreateListener roomCreateListener;
    private EditText input_room;
    private EditText input_time;
    private EditText input_place;
    private Button btn_create_room;
    private Button btn_cancel_room;

    private String roomName = "";
    private String roomTime = "";
    private String roomPlace = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        input_room = findViewById(R.id.input_room);
        input_time = findViewById(R.id.input_time);
        input_place = findViewById(R.id.input_place);
        btn_create_room = (Button)findViewById(R.id.btn_create_room);
        btn_cancel_room = (Button)findViewById(R.id.btn_create_room);

        btn_create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomName = input_room.getText().toString();
                roomTime = input_time.getText().toString();
                roomPlace = input_place.getText().toString();

                //RoomItem roomItem = new RoomItem(roomName, roomTime, roomPlace);
                Intent intent = new Intent(RoomCreateActivity.this, RoomListActivity.class);
                intent.putExtra("roomName", roomName);
                intent.putExtra("roomTime", roomTime);
                intent.putExtra("roomPlace", roomPlace);
            }
        });
        btn_cancel_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}