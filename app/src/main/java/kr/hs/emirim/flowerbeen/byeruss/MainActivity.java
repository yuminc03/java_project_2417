package kr.hs.emirim.flowerbeen.byeruss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;


public class MainActivity extends AppCompatActivity{

    private final String TAG = "MainActivity";
    private String DB_PATH =  " /data/data/kr.hs.emirim.flowerbeen.byeruss/byeruss_room.db";

    //ListView myRoomList = null;

    EditText input_room;
    EditText input_time;
    EditText input_place;

    EditText text_input_code = null;
    Button btn_check = null;
    Button btn_cancel = null;

    //MyDBHandler mHandler = null;
    //Cursor mCursor = null;
    //SimpleCursorAdapter mAdapter = null;

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_find, btn_make;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myRoomList = (ListView)findViewById(R.id.my_room_list);
        //myRoomList.setOnItemLongClickListener(mLongClickListener);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);

        btn_find = (Button) findViewById(R.id.btn_find);//방 찾기
        btn_make = (Button) findViewById(R.id.btn_make);//방 만들기

        text_input_code = (EditText) findViewById(R.id.text_input_code);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        /*
        if( mHandler == null ) {
            mHandler = MyDBHandler.open(MainActivity.this, DB_PATH);
        }

         */

        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_froom();
            }
        });

        Button btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /*
    AdapterView.OnItemLongClickListener mLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            mCursor.moveToPosition(position);
            Log.d(TAG, "roomId : "+mCursor.getString(0) + "roomName : " + mCursor.getString(1));
            mHandler.delete(mCursor.getString(1));

            mCursor = mHandler.select();  // DB 새로 가져오기
            mAdapter.changeCursor(mCursor); // Adapter에 변경된 Cursor 설정하기
            mAdapter.notifyDataSetChanged(); // 업데이트 하기

            return true;
        }
    };

    public void insertToDB() {
        Log.d(TAG, "insertToDB");

        String room, time, place;
        if( (input_room.getText() != null) && (input_time.getText() != null) && (input_place.getText() != null)) {
            room = input_room.getText().toString();
            time = input_time.getText().toString();
            place = input_place.getText().toString();
        }
        else {
            Toast.makeText(MainActivity.this, "Please enter the all members", Toast.LENGTH_SHORT).show();
            return;
        }

        mHandler.insert(room, time, place);

        mCursor = mHandler.select();  // DB 새로 가져오기
        mAdapter.changeCursor(mCursor); // Adapter에 변경된 Cursor 설정하기
        mAdapter.notifyDataSetChanged(); // 업데이트 하기

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.close();
    }


     */
    void show_froom() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_find_room, null);
        builder2.setView(view);
        final AlertDialog dialog = builder2.create();

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String RoomCode = text_input_code.getText().toString();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//메뉴버튼
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {//메뉴버튼 기능
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_btn:
                drawerLayout.openDrawer(drawerView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

}