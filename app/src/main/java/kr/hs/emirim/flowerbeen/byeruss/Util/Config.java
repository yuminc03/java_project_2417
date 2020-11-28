package kr.hs.emirim.flowerbeen.byeruss.Util;

public class Config {

    public static final String DATABASE_NAME = "byeruss_room.db";

    //멤버들이 있는 방 테이블
    public static final String ROOM_TABLE_NAME = "byeruss_make_room";
    public static final String COLUMN_ROOM_NAME = "roomName";
    public static final String COLUMN_ROOM_TIME = "roomTime";
    public static final String COLUMN_ROOM_PLACE = "roomPlace";

    //방에 있는 멤버 테이블
    public static final String MEMBER_TABLE_NAME = "byeruss_room_member";
    public static final String COLUMN_MEMBER_ID = "myRoomId";
    public static final String COLUMN_MEMBER_1 = "memberId";


    public static final String TITLE = "title";
    public static final String CREATE_ROOM = "create_room";
    public static final String UPDATE_ROOM = "update_room";
    public static final String FIND_ROOM = "find_room";

}
