package com.ml.testsecurity.controller;

import com.ml.testsecurity.entity.Menu;
import com.ml.testsecurity.entity.User;
import com.ml.testsecurity.mapper.CustomerMapper;
import com.ml.testsecurity.entity.Customer;
import com.ml.testsecurity.mapper.MenuMapper;
import com.ml.testsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author MaLei
 * @description: controller
 * @create 2020/7/14
 */
@RestController
@Slf4j
public class TestController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;
    //@Autowired
    // CustomerRepository customerRepository;
    @RequestMapping("/hello")
   // @Transactional("firstTransactionManager")
    @PreAuthorize("hasPermission('/hello','r') or hasRole('ROLE_admin')")
    public String hello(HttpSession session,HttpServletRequest r) {

        return  "hello";
    }
    @RequestMapping("/login")
    // @Transactional("firstTransactionManager")
    public String login() {
        return "/login";
    }
    @RequestMapping("/login_p")
    // @Transactional("firstTransactionManager")
    public ModelAndView login_p() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("mylogin.html");
        return mv;
    }
    @RequestMapping("/admin")
    // @Transactional("firstTransactionManager")
    public String admin() {
        return "/admin";
    }
    //@PreAuthorize("hasPermission('/hello', 'read') or hasRole('ROLE_user')")
    public String tt(String str){
        return str;
    }
}
