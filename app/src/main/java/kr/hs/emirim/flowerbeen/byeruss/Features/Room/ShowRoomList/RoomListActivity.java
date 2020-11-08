package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Database.MySQLiteOpenHelper;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.DialogCustomActivity;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.DialogFindActivity;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomCreateListener;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.R;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class RoomListActivity extends AppCompatActivity implements RoomCreateListener{

    private MyDBHandler myDBHandler = new MyDBHandler(this);

    private List<RoomItem> studentList = new ArrayList<>();

    private String userID;

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_find, btn_make;

    private TextView summaryTextView;
    private TextView studentListEmptyTextView;
    private RecyclerView recyclerView;
    private RoomListRecyclerViewAdapter roomListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Logger.addLogAdapter(new AndroidLogAdapter());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        btn_find = (Button) findViewById(R.id.btn_find);//방 찾기 다이얼로그
        btn_make = (Button) findViewById(R.id.btn_make);//방 만들기 다이얼로그

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        recyclerView = findViewById(R.id.recyclerView);
        studentListEmptyTextView = findViewById(R.id.emptyListTextView);

        studentList.addAll(myDBHandler.getAllRoomItem());

        roomListRecyclerViewAdapter = new RoomListRecyclerViewAdapter(this, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(roomListRecyclerViewAdapter);

        viewVisibility();

        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRoomCreateDialog();
            }
        });

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(item.getItemId()==R.id.action_delete){
//
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setMessage("Are you sure, You wanted to delete all students?");
//            alertDialogBuilder.setPositiveButton("Yes",
//                    new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1) {
//                            boolean isAllDeleted = myDBHandler.deleteAllRooms();
//                            if(isAllDeleted){
//                                studentList.clear();
//                                roomListRecyclerViewAdapter.notifyDataSetChanged();
//                                viewVisibility();
//                            }
//                        }
//                    });
//
//            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void viewVisibility() {
        if(studentList.isEmpty())
            studentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            studentListEmptyTextView.setVisibility(View.GONE);
    }

    private void openRoomCreateDialog() {
        DialogCustomActivity dialogCustomActivity = DialogCustomActivity.newInstance("Create Rooms", this);
        dialogCustomActivity.show(getSupportFragmentManager(), Config.CREATE_ROOM);
    }
//    private void openRoomFindDialog(){
//        DialogFindActivity dialogFindActivity = DialogFindActivity.newInstance("Find Rooms", this);
//        dialogFindActivity.show(getSupportFragmentManager(), Config.FIND_ROOM);
//    }

    private void printSummary() {
        long studentNum = myDBHandler.getNumberOfRoom();
        //long subjectNum = myDBHandler.getNumberOfSubject();

        //summaryTextView.setText(getResources().getString(R.string.database_summary, studentNum, subjectNum));
    }
    @Override
    public void onRoomCreated(RoomItem roomItem) {
        studentList.add(roomItem);
        roomListRecyclerViewAdapter.notifyDataSetChanged();
        viewVisibility();
        Logger.d(roomItem.getRoomName());
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