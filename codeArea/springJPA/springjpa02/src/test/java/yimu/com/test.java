package yimu.com;

import cn.itcast.dao.CustomerDao;
import cn.itcast.entity.Customer;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContex.xml")
public class test {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 查询所有数据
     */
    @Test
    public void test01(){
        List<Customer> all = customerDao.findAll();
        for(Customer customer:all){
            System.out.println(customer);
        }
    }

    /**
     * 更新或保存数据
     */
    @Test
    public void test02(){
        Customer customer = customerDao.findOne(2l);
        customer.setCustPhone("1212112121212");
        customer.setCustName("林林");
        customerDao.save(customer);
    }

    /**
     * 删除
     */

    @Test
    public void test03(){
        customerDao.delete(2l);
    }

    /**
     * 统计数据的个数
     */
    @Test
    public void test04(){
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 判断是否存在该数据
     */
    @Test
    public void test05(){
        boolean exists = customerDao.exists(4l);
        System.out.println(exists);
    }

    /**
     * 查询数据
     * 延迟加载
     * 务必加@Transactional，不然会报错
     */
    @Test
    @Transactional
    public void test06(){
        Customer one = customerDao.getOne(4l);
        System.out.println(one);
    }

    /**
     * Jpql语句
     */
    @Test
    public void test07() throws Exception{
        Customer name = customerDao.findCuntomerByNAME("大");
        System.out.println(name);
    }

    /**
     * 查询数据
     * @throws Exception
     */
    @Test
    public void test08() throws Exception{
        Customer customer = customerDao.findCus("haha", 6l);
        System.out.println(customer);

    }

    /**
     * 更新操作
     * @throws Exception
     */
    @Test
    @Transactional
    @Rollback(value = false)//默认情况下为true
    public void testUpdate() throws Exception {
        Integer integer = customerDao.updateCus("Edimen", 5l);
        System.out.println(integer);
    }



   @Test
    public void findAll() throws Exception{
        List<Object[]> obj = customerDao.findALL();

        for (Object[] o : obj){
            System.out.println(Arrays.toString(o));
        }
    }
    @Test
    public void findsql() throws Exception{
        List<Object[]> obj = customerDao.findSql("haha%");

        for (Object[] o : obj){
            System.out.println(Arrays.toString(o));
        }
    }




     @Test
    public void findByCustNameAndAndCustId()throws Exception{
        Customer haha = customerDao.findByCustNameAndAndCustId("haha", 6l);
        String json = JSONObject.toJSONString(haha);
        System.out.println(json);
    }
    //findByCustNameLike
    @Test
    public void findByCustNameLike()throws Exception{
        List<Customer> haha = customerDao.findByCustNameLike("haha%");
        for (Customer h:haha) {
            String json = JSONObject.toJSONString(h);
            System.out.println(json);
        }
    }

    @Test
    public void findByCustNameLikeAndCustId() throws Exception{
        List<Customer> cus = customerDao.findByCustNameLikeAndCustId("haha%", 6l);
            for(Customer c : cus){
                String json = JSONObject.toJSONString(c);
                System.out.println(json);
            }
    }
    @Test
    public void testFindAll(){
        List<Customer> all = customerDao.findAll();
        for (Customer c:all
             ) {
            System.out.println(c);

        }
    }
}
