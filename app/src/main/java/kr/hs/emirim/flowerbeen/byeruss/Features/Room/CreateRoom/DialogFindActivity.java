package kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import kr.hs.emirim.flowerbeen.byeruss.R;

public class DialogFindActivity extends AppCompatDialogFragment {

    private EditText text_input_code;
    private Button btn_check;
    private Button btn_cancel;

    public DialogFindActivity(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_find_room, container, false);

        text_input_code = view.findViewById(R.id.text_input_code);
        btn_check = view.findViewById(R.id.btn_check);
        btn_cancel = view.findViewById(R.id.btn_cancel);

        return view;
    }
}
