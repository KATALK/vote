package com.seventh.group.repository;

import com.seventh.group.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author EdiMen
 * @Data 2020/9/10--18:33
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
