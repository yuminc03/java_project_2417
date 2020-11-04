package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import kr.hs.emirim.flowerbeen.byeruss.R;

public class CustomViewHolder extends RecyclerView.ViewHolder{//room 데이터를 마치 list형식으로 보여주기

    TextView roomIdTextView;
    TextView roomNameTextView;
    TextView roomTimeTextView;
    TextView roomPlaceTextView;
    ImageView crossButtonImageView;
    ImageView editButtonImageView;

    public CustomViewHolder(View itemView) {
        super(itemView);

        roomIdTextView = itemView.findViewById(R.id.idTextView);
        roomNameTextView = itemView.findViewById(R.id.roomNameTextView);
        roomTimeTextView = itemView.findViewById(R.id.roomTimeTextView);
        roomPlaceTextView = itemView.findViewById(R.id.roomPlaceTextView);
        crossButtonImageView = itemView.findViewById(R.id.deleteImageView);
        editButtonImageView = itemView.findViewById(R.id.editImageView);
    }
}
