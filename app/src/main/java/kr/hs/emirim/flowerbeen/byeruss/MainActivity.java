package kr.hs.emirim.flowerbeen.byeruss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View drawerView;
    private Button btn_find, btn_make;
    private Context context;
    EditText input_room;
    EditText input_time;
    EditText input_place;
    private Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        btn_find = (Button)findViewById(R.id.btn_find);
        btn_make = (Button)findViewById(R.id.btn_make);

        btn_make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMakeDialog();
            }
        });

        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFindDialog();
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
    public void openFindDialog(){//모임 찾기 버튼기능
        FindRoomActivity findRoomActivity = new FindRoomActivity();
        findRoomActivity.show(getSupportFragmentManager(), "find room dialog");
    }
    public void openMakeDialog(){//모임 만들기 버튼기능
        DialogCustom makeRoomActivity = new DialogCustom();
        makeRoomActivity.show(getSupportFragmentManager(), "make room dialog");
    }

}