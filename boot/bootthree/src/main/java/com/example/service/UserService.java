package com.example.service;

import com.example.dao.UserDao;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**用户业务层
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    //根据用户名查询用户
    public User findUserByUserName(String username) {
       return userDao.findByUsername(username);
    }
    //查询所有
    public List<User> findAll(){
       return userDao.findAll();
    }
    //添加用户
    @Transactional
    public User saveUser(User user) {
        return userDao.save(user);
    }
    //根据主键id查询用户
    public User findUserById(Long id){
        Optional<User> u = userDao.findById(id);
        User user = u.get();
        return user;
    }
    //更新用户数据
    public User updateUser(User user) {
        return userDao.saveAndFlush(user);
    }
    //根据用户id删除用户数据
    public void deleteUser(long id) {
        userDao.deleteById(id);
    }
}
