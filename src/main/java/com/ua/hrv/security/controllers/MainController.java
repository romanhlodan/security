package com.ua.hrv.security.controllers;

import com.ua.hrv.security.models.Customer;
import com.ua.hrv.security.service.CustomerService;
import com.ua.hrv.security.utils.CustomerEditor;
import com.ua.hrv.security.utils.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PropertySource(("classpath:validation.properties"))
public class MainController {

    @Autowired
    private Environment environment;
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/ok")
    public String ok(){
        return "success";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    CustomerEditor customerEditor;
    @Autowired
    CustomerValidator customerValidator;
    @PostMapping("/save")
    public String save (Customer customer, BindingResult result, Model model){
        customerValidator.validate(customer, result);
        if (result.hasErrors()){
            String errorMessage = "";
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error : allErrors) {
                errorMessage += " "+ environment.getProperty(error.getCode());

                model.addAttribute("error", errorMessage);
            }
            return "index";
        }



        customerEditor.setValue(customer);
//        customerService.save(customer);
//        String password = customer.getPassword();
//        String encode = passwordEncoder.encode(password);
//        customer.setPassword(encode);

//        customer.setPassword(passwordEncoder.encode(customer.getPassword()));


        customerService.save(customer);
        return "login";
    }



}
