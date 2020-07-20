package com.ml.testsecurity.configuration;

import com.ml.testsecurity.entity.Menu;
import com.ml.testsecurity.entity.Role;
import com.ml.testsecurity.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * @author MaLei
 * @description: PermissionEvaluator实现类，如果本类开启@Configuration，则高版本springboot中会自动注册到springsecurity中，
 * 需要将配置类中HttpSecurity的实体后置处理器代码去掉，如下代码去掉
 * http.authorizeRequests()
 *                 .anyRequest().authenticated() .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>(){
 *            @Override
 *            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
 *                o.setSecurityMetadataSource(metadataSource);
 *                o.setAccessDecisionManager(urlAccessDecisionManager);
 *                return o;
 *            }
 *        })
 * @create 2020/7/17
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean accessable = false;
        if(authentication.getPrincipal().toString().compareToIgnoreCase("anonymousUser") != 0){
            Menu menu= menuMapper.selectByRequestUrl(targetDomainObject.toString());
            if(menu==null) return accessable;
            List<Role> roles = menu.getRoles();
            Iterator<Role> it=roles.iterator();
            while(it.hasNext()) {
                Role role=it.next();
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                   if(role.getName().equals(authority.getAuthority())){
                       accessable=true;
                   }
                }
            }
        }
        return accessable;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
