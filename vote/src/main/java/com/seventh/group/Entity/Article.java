package com.seventh.group.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author EdiMen
 * @Data 2020/9/10--13:33
 * @Version 1.0
 */


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
    private List<User> users ;

    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Option> options ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Article() {
        super();
    }

    public Article(String title, int type, Date createTime, List<User> users, List<Option> options) {
        this.title = title;
        this.type = type;
        this.createTime = createTime;
        this.users = users;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id &&
                type == article.type &&
                title.equals(article.title) &&
                createTime.equals(article.createTime) &&
                users.equals(article.users) &&
                options.equals(article.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, createTime, users, options);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", users=" + users +
                ", options=" + options +
                '}';
    }
}
