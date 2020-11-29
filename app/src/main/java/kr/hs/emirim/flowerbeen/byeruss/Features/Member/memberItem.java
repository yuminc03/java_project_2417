package kr.hs.emirim.flowerbeen.byeruss.Features.Member;

public class memberItem {
    private int memberNumber;
    private String myRoomId;
    private String memberId;

    public memberItem(int memberNumber, String myRoomId, String memberId){
        this.memberNumber = memberNumber;
        this.myRoomId = myRoomId;
        this.memberId = memberId;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
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
