package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

    private ListView meetinglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);

        meetinglist = (ListView)findViewById(R.id.meetinglist);
        // listview 생성 및 adapter 지정

        List<String> data = new ArrayList<String>();
        // 빈 데이터 리스트 생성

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,data);
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.

        meetinglist.setAdapter(adapter);

    }
}