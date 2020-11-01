package kr.hs.emirim.flowerbeen.byeruss.Features.Room;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import kr.hs.emirim.flowerbeen.byeruss.R;

public class DialogCustomActivity extends AppCompatDialogFragment {

    private static RoomCreateListener roomCreateListener;

    private EditText input_room;
    private EditText input_time;
    private EditText input_place;

    private String roomName = "";
    private String roomTime = "";
    private String roomPlace = "";

    public DialogCustomActivity(){

    }

    public static DialogCustomActivity newInstance(String title, RoomCreateListener listener){
        roomCreateListener = listener;
        DialogCustomActivity dialogCustomActivity = new DialogCustomActivity();
        Bundle args = new Bundle();//Bundle = 여러가지의 타입의 값을 저장하는 Map 클래스
        //Bundle은 아무거나 포장할 수 있는 상자를 의미하고 이 포장 박스를 이용하여
        // 이리저리 인텐트도 오고갈 수 있고 다양한 데이터 통신에 이용 할 수 있다.
        args.putString("title", title);
        dialogCustomActivity.setArguments(args);//값을 전달한다

        dialogCustomActivity.setStyle(AppCompatDialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return dialogCustomActivity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_custom, null);

        input_room = (EditText)view.findViewById(R.id.input_room);
        input_time = (EditText)view.findViewById(R.id.input_time);
        input_place = (EditText)view.findViewById(R.id.input_place);

        builder.setView(view)
            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String roomName = input_room.getText().toString();
                String roomTime = input_time.getText().toString();
                String roomPlace = input_place.getText().toString();

            }
        })
            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        return builder.create();
    }

}

