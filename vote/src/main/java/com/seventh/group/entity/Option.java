package com.seventh.group.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author EdiMen
 * @Data 2020/9/10--15:04
 * @Version 1.0
 */
@Entity
@Table(name = "t_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//主键id

    private String content;//投票内容

    private int articleId;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},optional = false)
    //可选属性，optional=false,表示article不能为空。删除option不影响article
    @JoinColumn(name = "article_id")
    @JoinTable(name = "user_article_option",joinColumns = {@JoinColumn(name = "option_id")})
    private Article article;
}
