package kr.hs.emirim.flowerbeen.byeruss.Features.Room;

public class RoomItem {
    private int RoomId;
    private String RoomName;
    private String RoomTime;
    private String RoomPlace;

    public RoomItem(String RoomName, String RoomTime, String RoomPlace){
        //this.RoomId = RoomId;
        this.RoomName = RoomName;
        this.RoomTime = RoomTime;
        this.RoomPlace = RoomPlace;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int roomId) {
        RoomId = roomId;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getRoomTime() {
        return RoomTime;
    }

    public void setRoomTime(String roomTime) {
        RoomTime = roomTime;
    }

    public String getRoomPlace() {
        return RoomPlace;
    }

    public void setRoomPlace(String roomPlace) {
        RoomPlace = roomPlace;
    }
}
