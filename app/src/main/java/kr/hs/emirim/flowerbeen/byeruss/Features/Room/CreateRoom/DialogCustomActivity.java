package kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.R;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class DialogCustomActivity extends AppCompatDialogFragment {

    private static RoomCreateListener roomCreateListener;

    private EditText input_room;
    private EditText input_time;
    private EditText input_place;
    private Button btn_create_room;
    private Button btn_cancel_room;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dialog_custom, container, false);

        input_room = view.findViewById(R.id.input_room);
        input_time = view.findViewById(R.id.input_time);
        input_place = view.findViewById(R.id.input_place);
        btn_create_room = view.findViewById(R.id.btn_create_room);
        btn_cancel_room = view.findViewById(R.id.btn_create_room);

        String title = getArguments().getString(Config.TITLE);
        getDialog().setTitle(title);

        btn_create_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomName = input_room.getText().toString();
                roomTime = input_time.getText().toString();
                roomPlace = input_place.getText().toString();

                RoomItem roomItem = new RoomItem(roomName, roomTime, roomPlace);

                MyDBHandler myDBHandler = new MyDBHandler(getContext());


                int id = myDBHandler.insertRoom(roomItem);

                if(id>0){
                    roomItem.setRoomId(id);
                    roomCreateListener.onRoomCreated(roomItem);
                    getDialog().dismiss();
                }
            }
        });
        btn_cancel_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //no inspection Constant Conditions
            //검사 없음 일정한 조건
            dialog.getWindow().setLayout(width, height);
        }
    }

    /*
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

     */

}

