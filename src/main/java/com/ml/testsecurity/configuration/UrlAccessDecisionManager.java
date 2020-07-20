package com.ml.testsecurity.configuration;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author MaLei
 * @description: 自定义配置决策器：roleAccessDecisionManager
 * 权限决策 AccessDecisionManager
 *
 * 有了权限资源CustomMetadataSource，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了。
 *
 * 这需要通过实现自定义的AccessDecisionManager来实现
 * @create 2020/7/16
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    //decide方法的三个参数中：
    //  authentication包含了当前的用户信息，包括拥有的权限。这里的权限来源就是前面登录时UserDetailsService中设置的authorities。
    //  object就是FilterInvocation对象，可以得到request等web资源。
    //  configAttributes是本次访问需要的权限。
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas){
        Iterator<ConfigAttribute> iterator = cas.iterator();
        while (iterator.hasNext()) {
            if (auth == null) {
                throw new AccessDeniedException("当前访问没有权限");
            }
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
           /* if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else
                    return;
            }*/
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
