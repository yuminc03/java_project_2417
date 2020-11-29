package kr.hs.emirim.flowerbeen.byeruss.Features.Room;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.Features.Member.MemberListActivity;
import kr.hs.emirim.flowerbeen.byeruss.R;


public class RoomListActivity extends AppCompatActivity{

    private final String TAG = "RoomListActivity";
    private String DB_PATH =  "/data/data/kr.hs.emirim.flowerbeen.byeruss/byeruss_room.db";

    private ListView room_list_view;
    private String roomName, roomTime, roomPlace;

    private ArrayList arrayList = new ArrayList<>();
    private ArrayList<roomItem> roomitem = new ArrayList<>();
    private ArrayAdapter listAdapter;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;
    //cursor adapter는 커서로 읽은 정보를 list로 만들어 주는 역할을 한다.
    //따라서 DB에서 읽은 정보를 listview 형태로 보여줄때 사용한다.
    //Simple Cursor Adapter : cursor adatper중에 가장 간단한 adapter 이다.
    //Simple cursor adatper는 cursor에 있는 정보를 textView나 imageView로 보여줄때 사용한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

        room_list_view = findViewById(R.id.room_list_view);
        room_list_view.setOnItemLongClickListener(mLongClickListener);

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        selectRoom(listAdapter, mySQLiteOpenHelper, room_list_view);

        room_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {//모임을 클릭했을 때 intent이동
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MemberListActivity.class);
                intent.putExtra("roomName", roomName);
                startActivity(intent);
            }
        });

    }
    AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RoomListActivity.this);
            builder.setTitle("삭제");
            builder.setMessage("정말로 방을 삭제하겠습니까?");

            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try{
//                        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
//                        sqLiteDatabase.execSQL("DELETE FROM byeruss_make_room WHERE roomName = " + roomName + ";", null);
//                        sqLiteDatabase.close();
                        deleteRoom(roomName);
                        selectRoom(listAdapter, mySQLiteOpenHelper, room_list_view);
                        Toast.makeText(getApplicationContext(), "모임이 삭제되었습니다!!", Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }finally{
                        if(sqLiteDatabase != null) sqLiteDatabase.close();
                    }
                }
            });
            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();

            return true;
        }
    };

    public void deleteRoom(String roomName){
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM byeruss_make_room WHERE roomName = " + roomName);
        sqLiteDatabase.close();
    }

    public void selectRoom(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView){
        listAdapter.clear();
        roomitem.clear();

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM byeruss_make_room", null);//selectionArgs는 Where과 같이 인자가 필요한 필터의 메개변수 값을 전달 하는 역할을 한다.
        if(cursor != null){
            cursor.move(0);
            while(cursor.moveToNext()) {
                roomName = cursor.getString(cursor.getColumnIndex("roomName"));
                roomTime = cursor.getString(cursor.getColumnIndex("roomTime"));
                roomPlace = cursor.getString(cursor.getColumnIndex("roomPlace"));
                roomitem.add(new roomItem(roomName, roomTime, roomPlace));//객체에 DB내용 담기
            }
            listView.setAdapter(listAdapter);
            for(roomItem roomitem : roomitem){
                listAdapter.add("모임이름: " + roomitem.getRoomName() + "\n" + "모임시간: " + roomitem.getRoomTime() + "\n" + "모임장소: " + roomitem.getRoomPlace() + "\n");
            }
            listAdapter.notifyDataSetChanged();
        }
    }

}