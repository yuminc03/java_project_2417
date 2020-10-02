package kr.hs.emirim.flowerbeen.byeruss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private ArrayList<MeetingInformation> arrayList;
    private Context context;//adapter에서 action들을 가져올 때 필요(apater에서는 context단자가 없기 때문


    public CustomAdapter(ArrayList<MeetingInformation> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//실제 리스트뷰가 어뎁터에 연결된 다음에 뷰홀더를 최초로 만들어냄
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {//각 아이템들에 대한 매칭을 해줌
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);//서버로부터 이미지를 받아온다
        holder.tv_roomName.setText(arrayList.get(position).getRoomName());
        holder.tv_roomTime.setText(arrayList.get(position).getRoomTime());
        holder.tv_roomPlace.setText(arrayList.get(position).getRoomPlace());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);//삼항 연산자
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_roomName;
        TextView tv_roomTime;
        TextView tv_roomPlace;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);//상속받은 itemview에서 아이디값을 찾아온다
            this.tv_roomName = itemView.findViewById(R.id.tv_roomName);
            this.tv_roomTime = itemView.findViewById(R.id.tv_roomTime);
            this.tv_roomPlace = itemView.findViewById(R.id.tv_roomPlace);
        }
    }
}
