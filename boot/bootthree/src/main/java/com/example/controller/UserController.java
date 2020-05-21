package com.example.controller;


import com.example.pojo.User;
import com.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username")String username) {
        return userService.findUserByUserName(username);
    }

    @ApiOperation(value = "用户列表",notes = "用户列表")
    @RequestMapping(value = {""},method = RequestMethod.GET)
    public List<User> getUser() {
        return userService.findAll();
    }

    @ApiOperation(value = "创建用户",notes = "创建用户")
    @PostMapping(value = "")
    public User postUser(@RequestBody User user) {
        return  userService.saveUser(user);
    }

    @ApiOperation(value = "获取用户信息",notes = "根据url的id来获取用户信息")
    @GetMapping("/i/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value="更新信息",notes = "根据url的id来指定更新用户信息")
    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id,@RequestBody User user) {
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setId(id);
        return userService.updateUser(u);
    }

    @ApiOperation(value="删除用户",notes = "根据url的id来指定删除用户")
    @DeleteMapping(value = "/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "success";
    }

}
