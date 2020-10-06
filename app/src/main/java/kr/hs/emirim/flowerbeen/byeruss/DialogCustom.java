package kr.hs.emirim.flowerbeen.byeruss;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogCustom extends AppCompatDialogFragment {

    private Context context;
    private EditText input_room, input_time, input_place;
    private int room_code_int;
    private String room_code, roomCode;
    private String roomName, roomTime, roomPlace;

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
                        room_code_int = (int) (Math.random() * 1000000000);
                        room_code = Integer.toString(room_code_int);

                        roomCode = room_code;

                        roomName = input_room.getText().toString();
                        roomTime = input_time.getText().toString();
                        roomPlace = input_place.getText().toString();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }
}
