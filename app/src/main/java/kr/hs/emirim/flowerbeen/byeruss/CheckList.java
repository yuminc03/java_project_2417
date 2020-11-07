package kr.hs.emirim.flowerbeen.byeruss;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;


public class CheckList extends AppCompatDialogFragment {
    private ImageButton btn_back;
    private CheckBox cb_temp;
    private CheckBox cb_mask;
    private CheckBox cb_self_isolation;
    private CheckBox cd_fever;
    private Button btn_submit;

    public CheckList(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.check_list, container, false);

        btn_back = view.findViewById(R.id.btn_back);
        cb_temp = view.findViewById(R.id.cb_temp);
        cb_mask = view.findViewById(R.id.cb_mask);
        cb_self_isolation = view.findViewById(R.id.cb_self_isolation);
        cd_fever = view.findViewById(R.id.cd_fever);
        btn_submit = view.findViewById(R.id.btn_submit);

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
}