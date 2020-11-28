package kr.hs.emirim.flowerbeen.byeruss.Features.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import kr.hs.emirim.flowerbeen.byeruss.MainActivity;
import kr.hs.emirim.flowerbeen.byeruss.R;

public class RoomFindActivity extends AppCompatActivity {

    private EditText text_input_code;
    private Button btn_check;
    //private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_find);

        text_input_code = findViewById(R.id.text_input_code);
        btn_check = findViewById(R.id.btn_check);
        //btn_cancel = findViewById(R.id.btn_cancel );

//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RoomFindActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}