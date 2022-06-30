package pl.tom.todo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Autowired
//    DataSource dataSource;
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
////                .usersByUsernameQuery("SELECT login,password,user_id" + " FROM users" + " where login = ?")
////                .authoritiesByUsernameQuery("SELECT U.login, R.roles FROM users AS U, user_roles AS R WHERE (R.user_user_id=U.user_id AND U.login=?)");
//    }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
//            CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//            customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//            http.authorizeRequests().antMatchers("/api/login/**").permitAll();
//            http.authorizeRequests().antMatchers(OPTIONS,"/api/login/**").permitAll();
//            http.authorizeRequests().antMatchers(GET,"/tasks").permitAll();
//            http.authorizeRequests().antMatchers("/tasks").hasAnyAuthority("USER","ADMIN");
//            http.authorizeRequests().antMatchers("/test").authenticated();
//            http.authorizeRequests().antMatchers("/**").authenticated();
//            http.authorizeRequests().anyRequest().authenticated();
//            http.csrf().disable();
//            http.addFilter(customAuthenticationFilter);
//            http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//            http.headers().httpStrictTransportSecurity().disable();
            http.cors();

            http.authorizeRequests().anyRequest().permitAll();
            http.headers().httpStrictTransportSecurity().disable();
            http.csrf().disable();
        }
}