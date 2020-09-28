package kr.hs.emirim.flowerbeen.byeruss;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class DrawerActivity extends AppCompatActivity {

    private Button btn_close, btn_make, btn_find, btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        btn_close = (Button)findViewById(R.id.btn_close);
        btn_make = (Button)findViewById(R.id.btn_make);
        btn_find = (Button)findViewById(R.id.btn_find);
        btn_logout = (Button)findViewById(R.id.btn_logout);

    }
}