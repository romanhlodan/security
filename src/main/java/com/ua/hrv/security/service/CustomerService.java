package com.ua.hrv.security.service;

import com.ua.hrv.security.models.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {

    void save (Customer customer);
}
