package cn.yimu.config;

import cn.yimu.service.impl.userDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity //开启MVC security 支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private userDetailServiceImpl userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码需要设置编码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //使用jdbc进行身份认证
//        String userSql="select username,password,status from sys_user where username = ?";
//        String authoritySql = "select u.username,r.ROLE_NAME from sys_user u,sys_role r,sys_user_role ur where ur.uid=u.id and ur.rid = r.id and u.username=?";
//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("yimu").password(encoder.encode("123456")).roles("common")
//                .and()
//                .withUser("pj").password(encoder.encode("123456")).roles("VIP");
//        auth.jdbcAuthentication().passwordEncoder(encoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery(userSql)
//                .authoritiesByUsernameQuery(authoritySql);
        auth.userDetailsService(userDetailService).passwordEncoder(encoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                //需要对static文件夹下静态资源进行统一放行
                .antMatchers("/login/**").permitAll()
                .antMatchers("/detail/common/**").hasAnyRole("common","vip")
                .antMatchers("/detail/vip/**").hasRole("vip")
                .anyRequest().authenticated();
                //自定义用户登录控制
        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("pwd")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/userLogin?error");
        //自定义用户退出
        http.logout()
                .logoutUrl("/mylogout")
                .logoutSuccessUrl("/");
        //定制Remember-me记住我功能
        http.rememberMe()
                .rememberMeParameter("rememberme")
                .tokenValiditySeconds(200)
                .tokenRepository(tokenRepository());
        //关闭spring Security默认开启的CSRF防护功能
        //http.csrf().disable();
    }

    //持久化Token存储
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }
}
