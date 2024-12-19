package com.xlm.mmwave.controller;

import com.xlm.mmwave.service.UserService;
import com.xlm.mmwave.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xlm.mmwave.utils.Res;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 添加数据
    @PostMapping
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User added successfully.";
    }

    // 删除数据
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        return "User deleted successfully.";
    }

    // 更新数据
    @PutMapping
    public String updateUser(@RequestBody User user) {
        userService.modifyUser(user);
        return "User updated successfully.";
    }

    // 查询数据（通过 ID）
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // 查询所有数据
    @GetMapping
    public Res getAllUsers() {
        return Res.ok(userService.getAllUsers());
    }
}
