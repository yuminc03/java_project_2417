package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomCreateListener;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class RoomListActivity extends AppCompatActivity implements RoomCreateListener{

    private MyDBHandler myDBHandler = new MyDBHandler(this);

    private List<RoomItem> studentList = new ArrayList<>();

    private TextView studentListEmptyTextView;
    private RecyclerView recyclerView;
    private RoomListRecyclerViewAdapter roomListRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        Logger.addLogAdapter(new AndroidLogAdapter());


        recyclerView = findViewById(R.id.recyclerView);
        studentListEmptyTextView = findViewById(R.id.emptyListTextView);

        studentList.addAll(myDBHandler.getAllRoomItem());

        roomListRecyclerViewAdapter = new RoomListRecyclerViewAdapter(this, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(roomListRecyclerViewAdapter);

        viewVisibility();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void viewVisibility() {
        if(studentList.isEmpty())
            studentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            studentListEmptyTextView.setVisibility(View.GONE);
    }

    @Override
    public void onRoomCreated(RoomItem roomItem) {
        studentList.add(roomItem);
        roomListRecyclerViewAdapter.notifyDataSetChanged();
        viewVisibility();
        Logger.d(roomItem.getRoomName());
    }

}