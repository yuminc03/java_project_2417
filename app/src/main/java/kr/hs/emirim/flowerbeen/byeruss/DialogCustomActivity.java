package kr.hs.emirim.flowerbeen.byeruss;

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

public class DialogCustomActivity extends AppCompatDialogFragment {
    EditText input_room;
    EditText input_time;
    EditText input_place;
    private ExampleDialogListener listener;
    MainActivity mainActivity;

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
                listener.applyTexts(roomName, roomTime, roomPlace);
                mainActivity.insertToDB();
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener)context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException (context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String roomName, String roomTime, String roomPlace);
    }

}

