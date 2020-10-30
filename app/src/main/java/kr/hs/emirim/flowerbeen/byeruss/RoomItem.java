package kr.hs.emirim.flowerbeen.byeruss;

public class RoomItem {
    private String RoomId;
    private String RoomName;
    private String RoomTime;
    private String RoomPlace;

    public RoomItem(String RoomId, String RoomName, String RoomTime, String RoomPlace){
        this.RoomId = RoomId;
        this.RoomName = RoomName;
        this.RoomTime = RoomTime;
        this.RoomPlace = RoomPlace;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
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
