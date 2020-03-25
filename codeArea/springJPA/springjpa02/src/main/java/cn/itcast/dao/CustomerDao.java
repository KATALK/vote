package cn.itcast.dao;

import cn.itcast.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {


    @Query(value = "from Customer where custName = ?1")
    public Customer findCuntomerByNAME(String cusName) throws Exception;

    /**
     * 多占位符查询
     * 要指定占位符的位置
     * @param cusname
     * @param id
     * @return
     * @throws Exception
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCus(String cusname,Long id) throws Exception;


    @Query(value = "update Customer set custName = ?1 where  custId = ?2")
    @Modifying//执行更新操作
    public Integer updateCus(String cusname,Long id) throws Exception;

    /**查询所有
     * nativeQuery = true代表本地sql查询，false代表jpql查询
     * @return
     * @throws Exception
     */
    @Query(value = "select * from cst_customer ",nativeQuery = true)
    public List<Object[]> findALL() throws Exception;

    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object[]> findSql(String name) throws Exception;


    /**
     * 方法命名规则查询
     * findBy后面加属性名，首字母大写
     * 不用谢@Query语句
     */
    public Customer findByCustNameAndAndCustId(String cusName,Long id) throws Exception;
    /**
     * 方法命名规则模糊查询
     * findBy后面加属性名，首字母大写
     * 不用谢@Query语句
     */
    public List<Customer> findByCustNameLike(String cusName) throws Exception;

    public List<Customer> findByCustNameLikeAndCustId(String cusName,Long id) throws Exception;
}
