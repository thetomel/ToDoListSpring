package pl.tom.todo.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.tom.todo.app.jwt.JsonObjectAuthenticationFilter;
import pl.tom.todo.app.jwt.JwtFilter;
import pl.tom.todo.app.jwt.handlers.RestAuthenticationFailureHandler;
import pl.tom.todo.app.jwt.handlers.RestAuthenticationSuccessHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter /*SomeOne said it would work, it worked.*/{
    @Autowired
    UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestAuthenticationSuccessHandler successHandler;
    private final RestAuthenticationFailureHandler failureHandler;

    public SecurityConfig(RestAuthenticationSuccessHandler successHandler, RestAuthenticationFailureHandler failureHandler) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
//    @Autowired
//    DataSource dataSource;

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
////                .usersByUsernameQuery("SELECT login,password,user_id" + " FROM users" + " where login = ?")
////                .authoritiesByUsernameQuery("SELECT U.login, R.roles FROM users AS U, user_roles AS R WHERE (R.user_user_id=U.user_id AND U.login=?)");
//    }
    //
@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/test/*"));
        return filterRegistrationBean;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
////                .authorizeRequests()
////                .anyRequest().authenticated()
//                .authorizeRequests().antMatchers("/test").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
//                .and().formLogin().permitAll()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new BasicAuthenticationEntryPoint(HttpStatus.UNAUTHORIZED));
//
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http
                    .authorizeRequests()
                    .antMatchers("/logon").permitAll()
                    .antMatchers("/*").authenticated()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().permitAll()
                    .and()
                    .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 1
                    .exceptionHandling()
                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        }

//        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("ADMIN")
//                .and().formLogin().permitAll().and().logout().and().csrf().disable();

//   }

   public JsonObjectAuthenticationFilter authenticationFilter() throws Exception{
        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
       authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
       authenticationFilter.setAuthenticationManager(super.authenticationManager());
       return authenticationFilter;
   }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
