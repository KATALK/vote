package com.seventh.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--13:28
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//主键

    private String username;//用户名

    private String password;//密码

    private int type;//

    private int status;

    private long number;//投票数
    @ManyToMany(mappedBy = "users")
    private List<Article> articles = new ArrayList<>();


}
