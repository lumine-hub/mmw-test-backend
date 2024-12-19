package com.xlm.mmwave.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class User {
    private int userID;       // 对应数据库的 userid
    private String username;   // 对应数据库的 username
    private Timestamp sTime;   // 对应数据库的 s_time，开始时间
    private Timestamp eTime;   // 对应数据库的 e_time，结束时间
    private int age;           // 对应数据库的 age
    private String sex;        // 对应数据库的 sex，性别
    private String text;       // 对应数据库的 text，文本信息
    private String filePath;
}
