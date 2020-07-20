package com.ml.testsecurity.configuration;

import com.ml.testsecurity.entity.Menu;
import com.ml.testsecurity.entity.Role;
import com.ml.testsecurity.mapper.MenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author MaLei
 * @description: 通过URL地址获取相应权限然后在获取相应的角色集合
 * 配置securityMetadataSource，可以通过url来获取角色名称
 * 权限资源 SecurityMetadataSource
 *
 * 要实现动态的权限验证，当然要先有对应的访问权限资源了。Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限，所以第一步需要实现SecurityMetadataSource。
 *
 * SecurityMetadataSource是一个接口，同时还有一个接口FilterInvocationSecurityMetadataSource继承于它，但FilterInvocationSecurityMetadataSource只是一个标识接口，对应于FilterInvocation，本身并无任何内容
 * 参考：https://www.ktanx.com/blog/p/4929
 * @create 2020/7/16
 */
@Component
@Transactional("firstTransactionManager")
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuMapper menuService;
    //做uri匹配规则,它主要用来做类URLs字符串匹配；
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    //获取某个受保护的安全对象object的所需要的权限信息,是一组ConfigAttribute对象的集合，如果该安全对象object不被当前SecurityMetadataSource对象支持,则抛出异常IllegalArgumentException。
    //该方法通常配合boolean supports(Class<?> clazz)一起使用，先使用boolean supports(Class<?> clazz)确保安全对象能被当前SecurityMetadataSource支持，然后再调用该方法。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        //通过Spring Security 封装，可以安全的拿到HttpServletRequest 和 HttpServletResponse对象
        String requestUrl = ((FilterInvocation) o).getRequest().getServletPath();
        //如果是登录页面则白名单放开
       /* if(StringUtils.equals(requestUrl,"/mylogin.html")){
            return null;
        }*/
       // List<Menu> allMenu = menuService.findAllMenu();
        Menu menu= menuService.selectByRequestUrl(requestUrl);
       // for (Menu menu : allMenu) {
            if (menu!=null&&antPathMatcher.match(menu.getUrl(), requestUrl)
                    &&menu.getRoles().size()>0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                //这个SecurityConfig配置类就是为了保存 ConfigAttribute 而存在
                return SecurityConfig.createList(values);
            }
       // }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
        //在上面的实现中如果没有匹配的url直接返回null，也就是没有配置权限的url默认都为白名单，想要换成默认是黑名单只要修改这里即可
        //return null;
    }
    //获取该SecurityMetadataSource对象中保存的针对所有安全对象的权限信息的集合。该方法的主要目的是被AbstractSecurityInterceptor用于启动时校验每个ConfigAttribute对象。
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    //这里clazz表示安全对象的类型，该方法用于告知调用者当前SecurityMetadataSource是否支持此类安全对象，只有支持的时候，才能对这类安全对象调用getAttributes方法。
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
