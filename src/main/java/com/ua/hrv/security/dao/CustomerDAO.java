package com.ua.hrv.security.dao;

import com.ua.hrv.security.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
//    @Query("select c from Customer  c where  c.username =: username")
//    Customer asd(@Param("username") String username)

    Customer findByUsername(String username);
}
