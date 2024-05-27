package site.rentofficevn.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import site.rentofficevn.security.CustomSuccessHandler;
import site.rentofficevn.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @   Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable()
                .authorizeRequests()
                        .antMatchers("/admin/building").authenticated()
                        .antMatchers("/admin/building/edit").hasAnyRole("MANAGER","ADMIN")
                        .antMatchers("/admin/building/edit/**").authenticated()
                        .antMatchers("/admin/customer").authenticated()
                        .antMatchers("/admin/customer/edit").hasAnyRole("MANAGER","ADMIN")
                        .antMatchers("/admin/customer/edit/**").authenticated()
                        .antMatchers("/admin/user**").hasRole("ADMIN")
                        .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll()
                .and()
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/login?incorrectAccount").and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");

                http.authenticationProvider(authenticationProvider());
                return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
