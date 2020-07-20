package com.ml.testsecurity.service;

import com.ml.testsecurity.entity.User;
import com.ml.testsecurity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MaLei
 * @description: UserDetailService
 * @create 2020/7/14
 */
@Component
@Slf4j
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserMapper customerMapper;
    @Override
    @Transactional("firstTransactionManager")

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录账号：{}",username);
        //数据库读取账户
        User cust=customerMapper.selectByUserNameContainsRoles(username);
        if(cust==null)
            throw new UsernameNotFoundException("账户不存在");
        return cust;
    }
}
