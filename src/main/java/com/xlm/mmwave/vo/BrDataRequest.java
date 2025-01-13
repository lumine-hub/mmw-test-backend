package com.xlm.mmwave.vo;

/**
 * @author xlm
 * 2025/1/6 下午9:43
 */
public class BrDataRequest {
    private int uid;
    private String start_time;
    private String end_time;

    // Getter 和 Setter 方法
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
