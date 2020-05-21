package cn.yimu.repository;

import cn.yimu.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByUsername(String userName);
}
