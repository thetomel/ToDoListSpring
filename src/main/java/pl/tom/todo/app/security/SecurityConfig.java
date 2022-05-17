package pl.tom.todo.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig /*extends WebSecurityConfigurerAdapter /*SomeOne said it would work, it worked.*/{
    UserDetailsService userDetailsService;
    //@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
//    @Autowired
//    DataSource dataSource;
    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource).getUserDetailsService();
////                .usersByUsernameQuery("SELECT login,password,user_id" + " FROM users" + " where login = ?")
////                .authoritiesByUsernameQuery("SELECT U.login, R.roles FROM users AS U, user_roles AS R WHERE (R.user_user_id=U.user_id AND U.login=?)");
//    }

    //@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
//                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
//                .antMatchers("/").permitAll()
//                .anyRequest().hasRole("ADMIN")
//                .and().formLogin().permitAll()
//                .and().logout().permitAll()//;
                .antMatchers("/**").permitAll()
                .and().csrf().disable(); //CSRF should be off after tests
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}