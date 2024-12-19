package com.xlm.mmwave.dao;

import com.xlm.mmwave.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    // 插入数据
    @Insert("INSERT INTO user (username, s_time, e_time, age, sex, text, file_path) " +
            "VALUES (#{username}, #{sTime}, #{eTime}, #{age}, #{sex}, #{text}, #{filePath})")
    @Options(useGeneratedKeys = true, keyProperty = "userID")
    void insertUser(User user);

    // 删除数据
    @Delete("DELETE FROM user WHERE user_id = #{userID}")
    void deleteUser(int userID);

    // 更新数据
    @Update("UPDATE user SET username = #{username}, s_time = #{sTime}, e_time = #{eTime}, " +
            "age = #{age}, sex = #{sex}, text = #{text}, file_path = #{filePath} WHERE user_id = #{userID}")
    void updateUser(User user);

    // 根据 ID 查询
    @Select("SELECT * FROM user WHERE user_id = #{userID}")
    User selectUserById(int userID);

    // 查询所有用户
    @Select("SELECT * FROM user")
    List<User> selectAllUsers();
}