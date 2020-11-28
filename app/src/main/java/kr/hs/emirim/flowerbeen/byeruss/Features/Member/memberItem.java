package kr.hs.emirim.flowerbeen.byeruss.Features.Member;

public class memberItem {
    private String myRoomId;
    private String memberId;

    public memberItem(String myRoomId, String memberId){
        this.myRoomId = myRoomId;
        this.memberId = memberId;
    }

    public String getMyRoomId() {
        return myRoomId;
    }

    public void setMyRoomId(String myRoomId) {
        this.myRoomId = myRoomId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
