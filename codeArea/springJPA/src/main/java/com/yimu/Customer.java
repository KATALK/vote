package com.yimu;

import javax.persistence.*;
import java.io.Serializable;
@Entity//声明实体类
@Table(name = "cst_customer")//建立实体类和表的映射关系
public class Customer implements Serializable {
    //声明当前私有属性为主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //配置主键的生成策略
    //指定和表中 cust_id 字段的映射关系
    @Column(name = "cust_id")
    private Long custId;
    @Column(name="cust_name") //指定和表中 cust_name 字段的映射关系
    private String custName;
    @Column(name="cust_source")//指定和表中 cust_source 字段的映射关系
    private String custSource;
    @Column(name="cust_industry")//指定和表中 cust_industry 字段的映射关系
    private String custIndustry;
    @Column(name="cust_level")//指定和表中 cust_level 字段的映射关系
    private String custLevel;
    @Column(name="cust_address")//指定和表中 cust_address 字段的映射关系
    private String custAddress;
    @Column(name="cust_phone")//指定和表中 cust_phone 字段的映射关系
    private String custPhone;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'' +
                '}';
    }
}
