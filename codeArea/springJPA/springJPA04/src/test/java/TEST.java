import itcast.dao.SysRoleDao;
import itcast.dao.SysUserDao;
import itcast.entity.SysRole;
import itcast.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContex.xml")
public class TEST {

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private SysRoleDao roleDao;
    @Test
    @Transactional
    @Rollback(false)
    public void test01(){
        SysUser sysUser = new SysUser();
        sysUser.setPassword("123");
        sysUser.setState(11);
        sysUser.setUsername("haha");

        SysRole sysRole = new SysRole();
        sysRole.setRoledesc("哈哈哈哈");
        sysRole.setRolename("大");
        sysUser.getRoles().add(sysRole);//配置用户到角色的关系
        sysRole.getUsers().add(sysUser);
       // userDao.save(sysUser);
       roleDao.save(sysRole);
    }
    @Test
    @Rollback(false)
    @Transactional
    public void test02(){
        SysUser one = userDao.findOne(3l);
        userDao.delete(one);
    }
}
