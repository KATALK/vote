package com.seventh.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--13:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //主键id

    private String title;//文章标题

    private int type; //文章类型，0代表可以投票，1代表不可以投票

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//文章创建时间

    @ManyToMany
    @JoinTable(name = "user_article_option",joinColumns = {@JoinColumn(name = "article_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Option> options = new ArrayList<>();


}
