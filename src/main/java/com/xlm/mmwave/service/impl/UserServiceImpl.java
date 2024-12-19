package com.xlm.mmwave.service.impl;

import com.xlm.mmwave.dao.UserMapper;
import com.xlm.mmwave.model.User;
import com.xlm.mmwave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    // 添加数据
    @Override
    public void addUser(User user) {
        userMapper.insertUser(user);
    }

    // 删除数据
    @Override
    public void removeUser(int id) {
        userMapper.deleteUser(id);
    }

    // 更新数据
    @Override
    public void modifyUser(User user) {
        userMapper.updateUser(user);
    }

    // 根据 ID 查询数据
    @Override
    public User getUserById(int id) {
        return userMapper.selectUserById(id);
    }

    // 查询所有数据
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }
}