package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomCreateListener;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class RoomListActivity extends AppCompatActivity{

    private final String TAG = "RoomListActivity";
    private String DB_PATH =  "/data/data/kr.hs.emirim.flowerbeen.byeruss/byeruss_room.db";

    ListView roomListView = null;

    MyDBHandler myDBHandler = null;
    Cursor cursor = null;
    SimpleCursorAdapter simpleCursorAdapter = null;

    private String roomName, roomTime, roomPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        Intent intent = getIntent();
        roomName = intent.getStringExtra("roomName");
        roomTime = intent.getStringExtra("roomTime");
        roomPlace = intent.getStringExtra("roomPlace");

        if( myDBHandler == null ) {
            myDBHandler = MyDBHandler.open(RoomListActivity.this, DB_PATH);
        }
        insertRoomData();
        cursor = myDBHandler.select();
        simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_activated_2,
                cursor, new String[]{"roomName", "roomTime", "roomPlace"}, new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        roomListView.setAdapter(simpleCursorAdapter);

        AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                cursor.moveToPosition(position);
                Log.d(TAG, "index : " + cursor.getString(0) + "roomName : " + cursor.getString(1));
                myDBHandler.delete(cursor.getString(1));

                cursor = myDBHandler.select();  // DB 새로 가져오기
                simpleCursorAdapter.changeCursor(cursor); // Adapter에 변경된 Cursor 설정하기
                simpleCursorAdapter.notifyDataSetChanged(); // 업데이트 하기

                return true;
            }
        };

    }
    public void insertRoomData() {
        Log.d(TAG, "insertRoomData");

        String room_name, room_time, room_place;

        if( (roomName != null) && (roomTime != null)
                && (roomPlace != null) ) {
            room_name = roomName;
            room_time = roomTime;
            room_place = roomPlace;
        }
        else {
            //Toast.makeText(DBActivity.this, "Please enter the all members", Toast.LENGTH_SHORT).show();
            return;
        }

        myDBHandler.insert(room_name, room_time, room_place);

        cursor = myDBHandler.select();  // DB 새로 가져오기
        simpleCursorAdapter.changeCursor(cursor); // Adapter에 변경된 Cursor 설정하기
        simpleCursorAdapter.notifyDataSetChanged(); // 업데이트 하기

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDBHandler.close();
    }

}