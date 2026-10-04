package com.mthree.academy.c458.vrishti_va.hello_security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //Let spring know this class is being used for configuration
@EnableWebSecurity //Spring should use this class to set itself up.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* Set up in memory authentication.
       Autowired: This method will run at program start up,
       passing in AuthenticationManagerBuilder - that Spring Security automatically creates, into it.

     */

    @Autowired
    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {

        //Setting up 2 users and their roles.
        //{noop} in the password, to tell spring not use a password encoder for this authentication, the password is still 'password'.
        //When hook up to database for authentication, we'll be properly encoding the passwords, and won't need anything like this.
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //A long chain of method calls

        http
            //Setting up antMatchers to match against paths in application,
            //and indicating the type of security for that path.
            //hasRole("..."): limits to a specific role.
            //permitAll: Allows everyone to access a path.
            .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()

                //anyRequest on the end, indicating the security for any request not matching an existing pattern.
                .anyRequest().hasRole("USER")
            .and()

            //Indicating login page and failure URL.
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?login_error=1")
                .permitAll()
            .and()

            //Indicating where the application redirects when logout is successful.
            .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }

}
