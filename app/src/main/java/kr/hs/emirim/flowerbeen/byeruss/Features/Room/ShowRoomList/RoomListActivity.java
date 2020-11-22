package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.MainActivity;
import kr.hs.emirim.flowerbeen.byeruss.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.R;


public class RoomListActivity extends AppCompatActivity{

    private final String TAG = "RoomListActivity";
    private String DB_PATH =  "/data/data/kr.hs.emirim.flowerbeen.byeruss/byeruss_room.db";

    private ListView room_list_view;
    private String roomName, roomTime, roomPlace;
    private Button back_to_menu;

    private ArrayList arrayList = new ArrayList<>();
    private ArrayList<RoomItem> roomitem = new ArrayList<RoomItem>();
    private Context context;

    MyDBHandler myDBHandler;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    //cursor adapter는 커서로 읽은 정보를 list로 만들어 주는 역할을 한다.
    //따라서 DB에서 읽은 정보를 listview 형태로 보여줄때 사용한다.
    //Simple Cursor Adapter : cursor adatper중에 가장 간단한 adapter 이다.
    //Simple cursor adatper는 cursor에 있는 정보를 textView나 imageView로 보여줄때 사용한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        ArrayList arrayList = new ArrayList<>();
        ArrayList<RoomItem> roomitem = new ArrayList<RoomItem>();

        room_list_view = findViewById(R.id.room_list_view);
        room_list_view.setOnItemLongClickListener(mLongClickListener);
        back_to_menu = findViewById(R.id.back_to_menu);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        show(listAdapter, mySQLiteOpenHelper, room_list_view);

        back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            cursor.moveToPosition(position);
            Log.d(TAG, "roomName : " + cursor.getString(0));
            myDBHandler.delete(cursor.getString(0));

            cursor = myDBHandler.select();  // DB 새로 가져오기
            simpleCursorAdapter.changeCursor(cursor); // Adapter에 변경된 Cursor 설정하기
            simpleCursorAdapter.notifyDataSetChanged(); // 업데이트 하기

            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDBHandler.close();
    }

    public void show(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView){
        listAdapter.clear();
        roomitem.clear();
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM byeruss_make_room", null);
        if(cursor != null){
            cursor.move(0);
            while(cursor.moveToNext()){
                roomName = cursor.getString(cursor.getColumnIndex("roomName"));
                roomTime = cursor.getString(cursor.getColumnIndex("roomTime"));
                roomPlace = cursor.getString(cursor.getColumnIndex("roomPlace"));
                roomitem.add(new RoomItem(roomName, roomTime, roomPlace));//객체에 DB내용 담기
            }
            listView.setAdapter(listAdapter);
            for(RoomItem roomitem : roomitem){
                listAdapter.add(roomitem.getRoomName() + "\n" + roomitem.getRoomTime() + "\n" + roomitem.getRoomPlace() + "\n");
            }
            listAdapter.notifyDataSetChanged();
        }
    }

}