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

    ListView room_list_view = null;

    MyDBHandler myDBHandler = null;
    Cursor cursor = null;
    SimpleCursorAdapter simpleCursorAdapter = null;
    //cursor adapter는 커서로 읽은 정보를 list로 만들어 주는 역할을 한다.
    //따라서 DB에서 읽은 정보를 listview 형태로 보여줄때 사용한다.

    //Simple Cursor Adapter : cursor adatper중에 가장 간단한 adapter 이다.
    //Simple cursor adatper는 cursor에 있는 정보를 textView나 imageView로 보여줄때 사용한다.

    private String roomName, roomTime, roomPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        room_list_view = (ListView)findViewById(R.id.room_list_view);
        room_list_view.setOnItemLongClickListener(mLongClickListener);

//        Intent intent = getIntent();
//        roomName = intent.getStringExtra("roomName");
//        roomTime = intent.getStringExtra("roomTime");
//        roomPlace = intent.getStringExtra("roomPlace");

        if( myDBHandler == null ) {
            myDBHandler = MyDBHandler.open(RoomListActivity.this, DB_PATH);
        }
        cursor = myDBHandler.select();
        simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.item_room,
                cursor, new String[]{"roomName", "roomTime", "roomPlace"}, new int[]{android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        room_list_view.setAdapter(simpleCursorAdapter);

    }
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