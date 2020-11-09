package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomFindActivity;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList.RoomListActivity;

public class ChoiceActivity extends AppCompatActivity {

    private TextView tv_user;
    private Button btn_make_room;
    private Button btn_find_room;
    private Button btn_my_room;
    private Button btn_log_out;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        tv_user = findViewById(R.id.tv_user);
        btn_make_room = (Button)findViewById(R.id.btn_make_room);
        btn_find_room = (Button)findViewById(R.id.btn_find_room);
        btn_my_room = (Button)findViewById(R.id.btn_my_room);
        btn_log_out = (Button)findViewById(R.id.btn_log_out);

        Intent i = getIntent();
        userID = i.getStringExtra("userID");

        tv_user.setText(userID);

        btn_make_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, RoomCreateActivity.class);
            }
        });
        btn_find_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, RoomFindActivity.class);
            }
        });
        btn_my_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, RoomListActivity.class);
            }
        });
    }
}