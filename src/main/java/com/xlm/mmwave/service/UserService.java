package com.xlm.mmwave.service;

import com.xlm.mmwave.model.User;

import java.util.List;

public interface UserService {

    // 添加数据
    void addUser(User user);

    // 删除数据
    void removeUser(int id);

    // 更新数据
    void modifyUser(User user);

    // 根据 ID 查询数据
    User getUserById(int id);

    // 查询所有数据
    List<User> getAllUsers();
}