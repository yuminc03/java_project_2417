package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import kr.hs.emirim.flowerbeen.byeruss.Features.Member.MemberListActivity;

public class checkListActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private CheckBox cb_temp;
    private CheckBox cb_mask;
    private CheckBox cb_fever;
    private CheckBox cb_wash_hand;
    private CheckBox cb_self_isolation;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        btn_back = findViewById(R.id.btn_back);
        cb_temp = findViewById(R.id.cb_temp);
        cb_mask = findViewById(R.id.cb_mask);
        cb_fever = findViewById(R.id.cb_fever);
        cb_wash_hand = findViewById(R.id.cb_wash_hand);
        cb_self_isolation = findViewById(R.id.cb_self_isolation);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = "";
                int r = 0;
                if(cb_temp.isChecked() == true) r++;
                if(cb_mask.isChecked() == true) r++;
                if(cb_fever.isChecked() == true) r++;
                if(cb_wash_hand.isChecked() == true) r++;
                if(cb_self_isolation.isChecked() == true) r++;
                result = Integer.toString(r); 
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(checkListActivity.this, MemberListActivity.class);
                startActivity(intent);
            }
        });
    }
}