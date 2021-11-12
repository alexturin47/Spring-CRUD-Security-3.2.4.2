package org.example.config;

//import org.example.service.UserSecurityService;
import org.example.config.handler.LoginSuccessHandler;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    public void setUserSecurityService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setLoginSuccessHandler(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password("1").roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/authentificate/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/profile/**").hasAnyRole("USER, ADMIN")
                .and()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/");



//        http.formLogin()
//                .loginPage("/login")
//                .successHandler(new loginSuccessHandler())
//                .loginProcessingUrl("/login")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .permitAll();
//
//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/logout?logout")
//                .and().csrf().disable();

//        http.authorizeRequests()
//                .antMatchers("/login").anonymous()
//                .antMatchers("/admin").access("hasAnyRole('ADMIN')").anyRequest().authenticated()
//                .antMatchers("/edit").access("hasAnyRole('ADMIN')").anyRequest().authenticated()
//                .antMatchers("/profile").access("hasAnyRole('USER')").anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService((UserDetailsService) userService);
        return daoAuthenticationProvider;
    }


}
