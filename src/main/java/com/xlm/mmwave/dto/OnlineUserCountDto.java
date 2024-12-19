package com.xlm.mmwave.dto;

public class OnlineUserCountDto {
    private int onlineUsrCnt;
    private int deltaOnlineUsrCnt;
    private int normalOnlineUsrCnt;
    private int warningOnlineUsrCnt;

    // Getters 和 Setters
    public int getOnlineUsrCnt() {
        return onlineUsrCnt;
    }

    public void setOnlineUsrCnt(int onlineUsrCnt) {
        this.onlineUsrCnt = onlineUsrCnt;
    }

    public int getDeltaOnlineUsrCnt() {
        return deltaOnlineUsrCnt;
    }

    public void setDeltaOnlineUsrCnt(int deltaOnlineUsrCnt) {
        this.deltaOnlineUsrCnt = deltaOnlineUsrCnt;
    }

    public int getNormalOnlineUsrCnt() {
        return normalOnlineUsrCnt;
    }

    public void setNormalOnlineUsrCnt(int normalOnlineUsrCnt) {
        this.normalOnlineUsrCnt = normalOnlineUsrCnt;
    }

    public int getWarningOnlineUsrCnt() {
        return warningOnlineUsrCnt;
    }

    public void setWarningOnlineUsrCnt(int warningOnlineUsrCnt) {
        this.warningOnlineUsrCnt = warningOnlineUsrCnt;
    }
}
