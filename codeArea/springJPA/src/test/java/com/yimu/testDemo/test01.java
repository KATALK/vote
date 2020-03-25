package com.yimu.testDemo;

import com.yimu.Customer;
import com.yimu.util.getEntityManager;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class test01 {
    /**
     * 添加数据
     */
    @Test
    public void test(){
//        /*** 创建实体管理类工厂，借助 Persistence 的静态方法获取 * 其中传递
//         * 的参数为持久化单元名称，需要 jpa 配置文件中指定 */
   EntityManagerFactory factory = Persistence.createEntityManagerFactory("Jpa");
      //创建实体管理类
        EntityManager entityManager = factory.createEntityManager();
        //EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务对象
        EntityTransaction transaction = entityManager.getTransaction();//同过工具类来获取EntityManager实体管理类
        //开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustAddress("盈盈");
      customer.setCustName("haha");
        //保存操作
        entityManager.persist(customer);
        //提交事务
        transaction.commit();
//        //释放资源
//        entityManager.close();
    }

    /**entityManager.find()立即加载
     * 查询数据
     */
    @Test
    public void test02(){
       //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        Customer customer = entityManager.find(Customer.class, 1L);
        System.out.println(customer);//打印查询到的数据
        transaction.commit();//提交事务
            entityManager.close();//释放资源
    }

    /**
     * 查询用户
     * entityManager.getReference()延迟加载
     */
    @Test
    public void test03(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        Customer customer = entityManager.getReference(Customer.class, 1L);
        System.out.println(customer);//打印查询到的数据
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 删除用户
     */
    @Test
    public void test04(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        Customer customer = entityManager.getReference(Customer.class, 1L);//查询数据
        entityManager.remove(customer);//删除数据
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 更新用户
     */
    @Test
    public void test05(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        Customer customer = entityManager.getReference(Customer.class, 2L);//查询数据
        customer.setCustName("EdiMen");
        customer.setCustPhone("12345678901");
        entityManager.merge(customer);//更新数据
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /***
     * 查询所有数据
     */
    @Test
    public void test06(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        //查询全部数据
        String jpql = "from com.yimu.Customer";
        //创建query查询对象,query对象才是jpql执行对象
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();

        //遍历集合数据

        for(Object object:resultList){
            System.out.println(object);
        }
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 倒叙查询
     */
    @Test
    public void test07(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        //查询倒叙的数据
        String jpql = "from com.yimu.Customer order by custId desc ";
        //创建query查询对象,query对象才是jpql执行对象
        Query query = entityManager.createQuery(jpql);
        //发送查询，并封装结果集
        List resultList = query.getResultList();

        //遍历集合数据

        for(Object object:resultList){
            System.out.println(object);
        }
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /***
     * 统计查询
     */
    @Test
    public void test08(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        //查询数据的数量
        String jpql = "select count(custId) from Customer ";
        //创建query查询对象,query对象才是jpql执行对象
        Query query = entityManager.createQuery(jpql);
        //返回一个对象
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 分页查询
     */
    @Test
    public void test09(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        //查询数据的数量
        String jpql = "from Customer ";
        //创建query查询对象,query对象才是jpql执行对象
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(0);//起始索引
        query.setMaxResults(3);//每次查询条数
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        //遍历集合数据

        for(Object object:resultList){
            System.out.println(object);
        }
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }

    /**
     * 条件查询
     */
    @Test
    public void test10(){
        //同过工具类来获取EntityManager实体管理类
        EntityManager entityManager = getEntityManager.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();//开启事务
        //查询数据的数量
        //String jpql = "from Customer where custAddress like '上海%'";
        String jpql = "from Customer where custAddress like ?";//占位符
        //创建query查询对象,query对象才是jpql执行对象
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,"上海%");
        //发送查询，并封装结果集
        List resultList = query.getResultList();
        //遍历集合数据

        for(Object object:resultList){
            System.out.println(object);
        }
        transaction.commit();//提交事务
        entityManager.close();//释放资源
    }
}
