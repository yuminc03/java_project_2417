package kr.hs.emirim.flowerbeen.byeruss.Features.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import kr.hs.emirim.flowerbeen.byeruss.CheckListActivity;
import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.RoomListActivity;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class MemberListActivity extends AppCompatActivity {


    private ListView member_list_view;
    private String myRoomId, memberId;
    private int memberNumber;
    private Button btn_back4;

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
        btn_back4 = findViewById(R.id.btn_back4);

        Intent intent = getIntent();
        String roomName = intent.getStringExtra("roomName");
        String memberId = intent.getStringExtra("memberId");
        myRoomId = roomName;
        this.memberId = memberId;

        member_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CheckListActivity.class);
                startActivity(intent);
            }
        });
        btn_back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberListActivity.this, RoomListActivity.class);
                startActivity(intent);
            }
        });

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        selectMember(listAdapter, mySQLiteOpenHelper, member_list_view);
    }

    public void selectMember(ArrayAdapter listAdapter, MySQLiteOpenHelper mySQLiteOpenHelper, ListView listView) {
        listAdapter.clear();
        memberitem.clear();

        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM byeruss_room_member WHERE myRoomId='"
                + myRoomId + "';", null);
        if (cursor != null) {
            cursor.move(0);
            while (cursor.moveToNext()) {
                memberNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex("memberNumber")));
                myRoomId = cursor.getString(cursor.getColumnIndex("myRoomId"));
                memberId = cursor.getString(cursor.getColumnIndex("memberId"));
                memberitem.add(new memberItem(memberNumber, myRoomId, memberId));//객체에 DB내용 담기
            }
            listView.setAdapter(listAdapter);
            for (memberItem memberitem : memberitem) {
                listAdapter.add("멤버 번호: " + memberitem.getMemberNumber() + "\n" + "모임이름: " + memberitem.getMyRoomId() + "\n" + "모임원: " + memberitem.getMemberId() + "\n");
            }
            listAdapter.notifyDataSetChanged();
        }
    }

}