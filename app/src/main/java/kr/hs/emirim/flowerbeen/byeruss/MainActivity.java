package kr.hs.emirim.flowerbeen.byeruss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "175.209.132.195";
    private static String TAG = "phptest";

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_find, btn_make;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        btn_find = (Button)findViewById(R.id.btn_find);//방 찾기
        btn_make = (Button)findViewById(R.id.btn_make);//방 만들기


        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_mroom();
            }
        });

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_froom();
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
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
    void show_mroom(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_custom, null);
        builder.setView(view);
        final EditText input_room = (EditText) view.findViewById(R.id.input_room);
        final EditText input_time = (EditText) view.findViewById(R.id.input_time);
        final EditText input_place = (EditText) view.findViewById(R.id.input_place);
        Button btn_create = (Button)view.findViewById(R.id.btn_create);
        Button btn_back = (Button)view.findViewById(R.id.btn_back);

        final AlertDialog dialog = builder.create();

        btn_create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int room_code_int = (int)(Math.random() * 1000000000);
                String room_code = Integer.toString(room_code_int);

                String roomCode = room_code;
                String roomName = input_room.getText().toString();
                String roomTime = input_time.getText().toString();
                String roomPlace = input_place.getText().toString();

                dialog.dismiss();//종료
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    void show_froom(){
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_find_room, null);
        builder2.setView(view);
        final EditText textInputCode = (EditText)view.findViewById(R.id.text_input_code);
        final Button btn_check = (Button)view.findViewById(R.id.btn_check);
        final Button btn_cancel = (Button)view.findViewById(R.id.btn_cancel);

        final AlertDialog dialog = builder2.create();

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String RoomCode = textInputCode.getText().toString();
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