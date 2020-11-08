package kr.hs.emirim.flowerbeen.byeruss.Features.Room.ShowRoomList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Member;
import java.util.List;

import kr.hs.emirim.flowerbeen.byeruss.Database.MyDBHandler;
import kr.hs.emirim.flowerbeen.byeruss.Features.Member.ShowMemberList.MemberListActivity;
import kr.hs.emirim.flowerbeen.byeruss.Features.Room.CreateRoom.RoomItem;
import kr.hs.emirim.flowerbeen.byeruss.R;
import kr.hs.emirim.flowerbeen.byeruss.Util.Config;

public class RoomListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<RoomItem> roomList;
    private MyDBHandler myDBHandler;

    public RoomListRecyclerViewAdapter(Context context, List<RoomItem> roomList) {
        this.context = context;
        this.roomList = roomList;
        myDBHandler = new MyDBHandler(context);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//RecyclerView가 RecyclerView.ViewHolder항목을 나타 내기 위해 지정된 유형 의 새로운 유형이 필요할 때 호출
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {//지정된 위치에 데이터를 표시하기 위해 RecyclerView에 의해 호출
        final int itemPosition = position;
        final RoomItem roomItem = roomList.get(position);

        holder.roomIdTextView.setText(roomItem.getRoomId());
        holder.roomNameTextView.setText(roomItem.getRoomName());
        holder.roomTimeTextView.setText(roomItem.getRoomTime());
        holder.roomPlaceTextView.setText(roomItem.getRoomPlace());

        holder.crossButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure, You wanted to delete this room?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteRoom(itemPosition);
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

//        holder.editButtonImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                RoomUpdateDialogFragment studentUpdateDialogFragment = RoomUpdateDialogFragment.newInstance(roomItem.getRegistrationNumber(), itemPosition, new StudentUpdateListener() {
//                    @Override
//                    public void onStudentInfoUpdated(RoomItem roomItem, int position) {
//                        studentList.set(position, roomItem);
//                        notifyDataSetChanged();
//                    }
//                });
//                studentUpdateDialogFragment.show(((StudentListActivity) context).getSupportFragmentManager(), Config.UPDATE_STUDENT);
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MemberListActivity.class);
                intent.putExtra(Config.COLUMN_ROOM_ID, roomItem.getRoomId());
                context.startActivity(intent);
            }
        });
    }

    private void deleteRoom(int position) {
        RoomItem roomItem = roomList.get(position);
        long count = myDBHandler.deleteRoomById(roomItem.getRoomId());

        if(count>0){
            roomList.remove(position);
            notifyDataSetChanged();
            ((RoomListActivity) context).viewVisibility();
            Toast.makeText(context, "Room deleted successfully", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(context, "Room not deleted. Something wrong!", Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

}
