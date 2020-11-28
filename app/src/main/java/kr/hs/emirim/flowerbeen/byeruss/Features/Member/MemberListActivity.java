package kr.hs.emirim.flowerbeen.byeruss.Features.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class MemberListActivity extends AppCompatActivity {


    private ListView member_list_view;
    private String myRoomId, memberId;

    private ArrayList arrayList = new ArrayList<>();
    private ArrayList<memberItem> memberitem = new ArrayList<>();
    private ArrayAdapter listAdapter;

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        member_list_view = findViewById(R.id.member_list_view);

        Intent intent = getIntent();
        String roomName = intent.getStringExtra("roomName");
        myRoomId = roomName;

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        selectMember(listAdapter, mySQLiteOpenHelper, member_list_view);

    }

    public void selectMember(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView){
        listAdapter.clear();
        memberitem.clear();

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM byeruss_room_member WHERE myRoomId='"
                + myRoomId + "';", null);
        if(cursor != null){
            cursor.move(0);
            while(cursor.moveToNext()){
                myRoomId = cursor.getString(cursor.getColumnIndex("myRoomId"));
                memberId = cursor.getString(cursor.getColumnIndex("memberId"));
                memberitem.add(new memberItem(myRoomId, memberId));//객체에 DB내용 담기
            }
            listView.setAdapter(listAdapter);
            for(memberItem memberitem : memberitem){
                listAdapter.add("모임이름: " + memberitem.getMyRoomId() + "\n" + "모임원: " + memberitem.getMemberId() + "\n");
            }
            listAdapter.notifyDataSetChanged();
        }
    }

}