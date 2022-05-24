package pl.tom.todo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.tom.todo.app.filters.CustomAuthenticationFilter;
import pl.tom.todo.app.filters.CustomAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter /*SomeOne said it would work, it worked.*/{
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
//@Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new CustomAuthenticationFilter(authenticationManager()));
//        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
//        return filterRegistrationBean;
//    }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
            customAuthenticationFilter.setFilterProcessesUrl("/api/login");
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.authorizeRequests().antMatchers("/api/login/**").permitAll();
            http.authorizeRequests().antMatchers("/tasks").permitAll();
            http.authorizeRequests().antMatchers("/test").authenticated();
            http.authorizeRequests().anyRequest().authenticated();
            http.csrf().disable();
            http.addFilter(customAuthenticationFilter);
            http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
}