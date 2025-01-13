package com.xlm.mmwave.vo;

import java.util.List;

public class BrDataResponse {
    private int uid;
    private List<Integer> hum_move_cnt;
    private List<Long> time_stamp;

    // Getter 和 Setter 方法
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Integer> getHum_move_cnt() {
        return hum_move_cnt;
    }

    public void setHum_move_cnt(List<Integer> hum_move_cnt) {
        this.hum_move_cnt = hum_move_cnt;
    }

    public List<Long> getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(List<Long> time_stamp) {
        this.time_stamp = time_stamp;
    }
}
