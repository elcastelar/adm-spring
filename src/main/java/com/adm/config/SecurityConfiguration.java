package com.adm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean(name = "httpSecurityFilter")
    public SecurityFilterChain httpSecurityFilter(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/javax.faces.resource/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/res/**", "/views/about_us.xhtml", "/views/privacy.xhtml", "/views/terms.xhtml")
                .permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/views/login.xhtml").permitAll()
                .loginProcessingUrl("/perform_client_login")
                .defaultSuccessUrl("/views/user/list-users.xhtml?testando")
                .failureUrl("/views/login.xhtml?message=failLogin")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
                .logout().logoutUrl("/perform_logout")
                .logoutSuccessUrl("/views/login.xhtml?message=sucsLogout")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean("myUserDetailsService")
    public UserDetailsService myUserDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userMngr = new JdbcUserDetailsManager(dataSource);
        userMngr.setUserExistsSql("SELECT username FROM tb_user WHERE username = ?");
        userMngr.setUsersByUsernameQuery("SELECT username,password,1 FROM tb_user WHERE username = ?");
        userMngr.setAuthoritiesByUsernameQuery("select t_user.username, user_role.name from tb_user AS t_user INNER JOIN tb_user_to_role as user_to_role on t_user.id = user_to_role.tb_user_id INNER JOIN tb_user_role as user_role ON user_to_role.userroles_id = user_role.id WHERE t_user.username = ?");
        return userMngr;
    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
