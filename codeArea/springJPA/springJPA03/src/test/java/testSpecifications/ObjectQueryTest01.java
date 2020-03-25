package testSpecifications;


import itcast.dao.CustomerDao;
import itcast.dao.LinkManDao;
import itcast.entity.Customer;
import itcast.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContex.xml")
public class ObjectQueryTest01 {
   @Autowired
    private CustomerDao customerDao;

   @Autowired
    private LinkManDao linkManDao;

   @Test
   @Transactional//解决could not initialize proxy - no Session
   public void test01(){
       Customer customer = customerDao.findOne(3l);
       Set<LinkMan> linkMans = customer.getLinkMans();

       for(LinkMan lm : linkMans){
           System.out.println(lm);
       }
   }
}
