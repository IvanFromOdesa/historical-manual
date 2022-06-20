package com.historicalreferencebook.historicalreferencebook.config;

import com.historicalreferencebook.historicalreferencebook.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
                 // change
        http.authorizeRequests()
                .antMatchers("/states/delete/**").hasAuthority("ADMIN")
                .antMatchers("/states/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("capitals/delete/**").hasAuthority("ADMIN")
                .antMatchers("/capitals/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/events/delete/**").hasAuthority("ADMIN")
                .antMatchers("/events/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/figures/delete/**").hasAuthority("ADMIN")
                .antMatchers("/figures/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/governors/delete/**").hasAuthority("ADMIN")
                .antMatchers("/governors/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/statistics/delete/**").hasAuthority("ADMIN")
                .antMatchers("/statistics/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/wars/delete/**").hasAuthority("ADMIN")
                .antMatchers("/wars/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                ;
    }
}

