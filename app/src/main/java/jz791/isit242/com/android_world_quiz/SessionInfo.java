package jz791.isit242.com.android_world_quiz;

import java.util.Date;

public class SessionInfo {
    String nickname;
    long sessionStart;  // in milliseconds
    int totalPoints;

    public SessionInfo(String nickname, long sessionStartInMillis, int totalPoints){
        this.nickname = nickname;
        this.sessionStart = sessionStartInMillis;
        this.totalPoints = totalPoints;
    }
}
