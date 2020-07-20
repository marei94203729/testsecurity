package com.ml.testsecurity.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.testsecurity.entity.User;
import com.ml.testsecurity.security.MD5PasswordEncoder;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MaLei
 * @description: 新建一个WebSecurityConfig类，使其继 承WebSecurityConfigurerAdapter
 * 在给WebSecutiryConfig类中加上@EnableWebSecurity 注解后，便会自动被 Spring发现并注册（查看
 * @EnableWebSecurity 即可看到@Configuration 注解已经存在
 * @create 2020/7/14
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomMetadataSource metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    //认证管理器配置方法可以配置定定义的UserDetailService和passwordEncoder。无需配置springboot2.3会自动注入bean
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }*/
    /*
    //也可以用此种方式注入相关的用户服务及密码验证器
       @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth)
	      throws Exception {
	        auth.inMemoryAuthentication().withUser("user")
	          .password(passwordEncoder().encode("password")).roles("USER");
	    }
     */
    //核心过滤器配置方法
    //void configure(WebSecurity web)用来配置 WebSecurity。而 WebSecurity是基于 Servlet Filter用来配置 springSecurityFilterChain。而 springSecurityFilterChain又被委托给了 Spring Security 核心过滤器 Bean DelegatingFilterProxy。  相关逻辑你可以在 WebSecurityConfiguration中找到。一般不会过多来自定义 WebSecurity, 使用较多的使其ignoring()方法用来忽略Spring Security对静态资源的控制.对于静态资源的忽略尽量在此处设置，否则容易无限循环重新定向到登录页面
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/mylogin.html","/admin", "/favicon.ico");
}

    //安全过滤器链配置方法
    //void configure(HttpSecurity http)这个是我们使用最多的，用来配置 HttpSecurity。 HttpSecurity用于构建一个安全过滤器链 SecurityFilterChain。SecurityFilterChain最终被注入核心过滤器 。 HttpSecurity有许多我们需要的配置。我们可以通过它来进行自定义安全访问策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http); 不能使用默认的验证方式
        //authorizeRequests（）方法实际上返回了一个 URL 拦截注册器，我们可以调用它提供的
        //anyanyRequest（）、antMatchers（）和regexMatchers（）等方法来匹配系统的URL，并为其指定安全
        //策略
       http.authorizeRequests()
                .anyRequest().authenticated() /*.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>(){
           @Override
           public <O extends FilterSecurityInterceptor> O postProcess(O o) {
               o.setSecurityMetadataSource(metadataSource);
               o.setAccessDecisionManager(urlAccessDecisionManager);
               return o;
           }
       })*/
                .and()
                //formLogin（）方法和httpBasic（）方法都声明了需要Spring Security提供的表单认证方式，分别返
                //回对应的配置器
                .formLogin()
                //，formLogin（）.loginPage（"/myLogin.html"）指定自定义的登录
                //页/myLogin.html，同时，Spring Security会用/myLogin.html注册一个POST路由，用于接收登录请求
               //loginProcessingUrl("/login")指定的/login必须与表单提交中指向的action一致
                   .loginPage("/mylogin.html").loginProcessingUrl("/logins").permitAll()
               //表单中用户名和密码对应参数设置(默认为username和password)，如果是默认值则不用设置下面的参数对应.
               .usernameParameter("usernames").passwordParameter("passwords")
               .successForwardUrl("/hello")
               .failureHandler(new AuthenticationFailureHandler() {
                   @Override
                   public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                       httpServletResponse.setContentType("application/json;charset=UTF-8");
                       httpServletResponse.setStatus(403);
                       String error=new String();
                       if (e instanceof BadCredentialsException ||
                               e instanceof UsernameNotFoundException) {
                           error="账户名或者密码输入错误!";
                       } else if (e instanceof LockedException) {
                           error="账户被锁定，请联系管理员!";
                       } else if (e instanceof CredentialsExpiredException) {
                           error="密码过期，请联系管理员!";
                       } else if (e instanceof AccountExpiredException) {
                           error="账户过期，请联系管理员!";
                       } else if (e instanceof DisabledException) {
                           error="账户被禁用，请联系管理员!";
                       } else {
                           error="登录失败!";
                       }
                       httpServletResponse.getWriter().write("{\"message\":\""+error+"\"}");
                   }
               })
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessHandler(new LogoutSuccessHandler() {
                   @Override
                   public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
                       resp.setContentType("application/json;charset=utf-8");

                      @Cleanup PrintWriter out = resp.getWriter();
                       out.write("{\"msg\":\"注销成功!\"}");
                       out.flush();
                      // out.close();
                   }
               })
               .permitAll()
                .and()
                //csrf（）方法是Spring Security提供的跨站请求伪造防护功能，当我们继承WebSecurityConfigurer
                //Adapter时会默认开启csrf（）方法
                .csrf().disable()
               //只有确实的访问失败才会进入AccessDeniedHandler，如果是未登陆或者会话超时等，不会触发AccessDeniedHandler，而是会直接跳转到登陆页面
       .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
           @Override
           public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
               httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
               httpServletResponse.setContentType("application/json;charset=UTF-8");
               PrintWriter out = httpServletResponse.getWriter();
               out.write(new ObjectMapper().writeValueAsString("{\"message\":\"权限不足，请联系管理员!\"}"));
               out.flush();
               out.close();
           }
       });

    }
    /**
     * 增加密码加密器，一旦增加，在验证过程中security将使用密码加密器进行加密对比，数据库中如果存储明文密码，在
     * UserDetailsService接口实现方法中，先加密密码然后才能返回UserDetails
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
      //  return new BCryptPasswordEncoder();
        return new MD5PasswordEncoder();
    }
    /**
     * 注入自定义PermissionEvaluator.低版本需要手动注册。高版本不需要，高版本注册将报错
     */
  /*  @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new MyPermissionEvaluator());
        return handler;
    }*/
}
