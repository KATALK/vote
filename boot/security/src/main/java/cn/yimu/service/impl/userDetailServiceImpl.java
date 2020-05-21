package cn.yimu.service.impl;

import cn.yimu.domain.Authority;
import cn.yimu.domain.Customer;
import cn.yimu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//实现UserDetailsService接口进行用户认证信息封装
@Service
public class userDetailServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomer(s);
        List<Authority> customerAuthority = customerService.getCustomerAuthority(s);
        //对权限用户进行封装
        List<SimpleGrantedAuthority> list = customerAuthority.stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());
        //返回封装的userDetail用户详情
        if (customer != null) {
            UserDetails userDetails = new User(customer.getUsername(), customer.getPassword(), list);
            return userDetails;
        } else {
            //如果查询的用户不存在（用户名不存在）,不许抛出异常
            throw new UsernameNotFoundException("当前用户不存在! ");
        }
    }
}
