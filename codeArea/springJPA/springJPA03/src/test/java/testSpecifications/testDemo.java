package testSpecifications;

import itcast.dao.CustomerDao;
import itcast.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContex.xml")
public class testDemo {
    //依赖注入customerDao
    @Autowired
    private CustomerDao customerDao;

    /**
     * 查询一个数据
     */
    @Test
    public void testSpecifications() {
        //使用匿名内部类的方式，创建一个Specification的实现类，并实现toPredicate方法
       Specification<Customer> specification = new Specification<Customer>() {
           @Override
           public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               Path<Object> path = root.get("custAddress");
               Predicate predicate = criteriaBuilder.equal(path, "上海");
               return  predicate;
           }
       };
        Customer one = customerDao.findOne(specification);
        System.out.println(one);
    }

    /**
     * 模糊查询
     * equal()：直接得到path对象(属性),然后进行比较即可
     * gt(),lt(),ge().le(),like(),得到path对象，根据path指定比较的参数类型，再去进行比较
     *      指定参数类型:path.as()
     */
    @Test
    public void testSpecifications01(){
        //使用匿名内部类的方式，创建一个Specification的实现类，并实现toPredicate方法
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custAddress = root.get("custAddress");

                return criteriaBuilder.like(root.get("custAddress").as(String.class),"%上%");
            }
        };
//        List<Customer> two = (List<Customer>) customerDao.findAll(specification);
//        for(Customer c:two){
//            System.out.println(c);
//        }
        //添加排序
        //创建排序，需要调用构造方法实例化sour对象
        //第一个参数：排序的规则,顺序还是倒叙或者其他
        //Sort.Direction.DESC倒叙
        //第二个参数：排序的属性名
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = customerDao.findAll(specification, sort);
        for(Customer c:all){
           System.out.println(c);
        }

    }

    /**
     * 多条件查询
     */
    @Test
    public void testSpecifications02(){
        //使用匿名内部类的方式，创建一个Specification的实现类，并实现toPredicate方法
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custAddress = root.get("custAddress");
                Predicate predicate = criteriaBuilder.equal(custAddress, "上海广东");
                Path<Object> custName = root.get("custName");
                Predicate predicate1 = criteriaBuilder.equal(custName, "11");
               // criteriaBuilder.or()或的形式查询，其中一个条件符合就可以
                Predicate or = criteriaBuilder.or(predicate, predicate1);
                Predicate and = criteriaBuilder.and(predicate, predicate1);
                return or;//将多个查询条件组合在一起，与，只有两个条件符合就通过
            }
        };
        List<Customer> two = (List<Customer>) customerDao.findAll(specification);
        for(Customer c:two){
            System.out.println(c);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        /**
         * 构造分页参数
         * Pageable : 接口
         * PageRequest实现了Pageable接口，调用构造方法的形式构造
         * 第一个参数：页码（从0开始）
         * 第二个参数：每页查询条数
         */
        Pageable pageable = new PageRequest(0,2);
        /**
         * 分页查询，封装为Spring Data Jpa 内部的page bean
         * 此重载的findAll方法为分页方法需要两个参数
         * 第一个参数：查询条件Specification
         * 第二个参数：分页参数
         */
        Page<Customer> all = customerDao.findAll(specification, pageable);
        for ( Customer c: all){
            System.out.println(c);
        }
        System.out.println(all.getContent());//得到数据集合列表
        System.out.println(all.getTotalPages());//得到总页数
        System.out.println(all.getTotalElements());//得到总条数
    }
}
