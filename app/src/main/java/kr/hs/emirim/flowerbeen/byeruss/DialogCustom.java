package kr.hs.emirim.flowerbeen.byeruss;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogCustom extends AppCompatDialogFragment {
    private Context context;
    private EditText input_room, input_time, input_place;
    private ExampleDialogListener listener;
    private int room_code_int;
    private final String code1 = "111111111";
    private String room_code, Room_code;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_custom, null);

        builder.setView(view)
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("생성", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        room_code_int = (int)(Math.random()*1000000000);
                        room_code = Integer.toString(room_code_int);
                        if(room_code != code1){
                            Room_code = room_code;
                        }
                        String roomName = input_room.getText().toString();
                        String meetTime = input_time.getText().toString();
                        String meetPlace = input_place.getText().toString();
                    }
                });
        input_room = view.findViewById(R.id.input_room);
        input_time = view.findViewById(R.id.input_time);
        input_place = view.findViewById(R.id.input_place);

        return builder.create();
    }
    public interface ExampleDialogListener{
        void applyTexts(String roomname, String meettime, String meetplece);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //listener = (ExampleDialogListener)
    }
}
