package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;

public class RoomCreateActivity extends AppCompatActivity {

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

                RoomItem roomItem = new RoomItem(roomName, roomTime, roomPlace);

                MyDBHandler myDBHandler = new MyDBHandler(getContext());

                int id = myDBHandler.insertRoom(roomItem);

                if(id>0){
                    roomItem.setRoomId(id);
                    roomCreateListener.onRoomCreated(roomItem);
                }
            }
        });
        btn_cancel_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}