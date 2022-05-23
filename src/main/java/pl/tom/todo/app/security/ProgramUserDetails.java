package pl.tom.todo.app.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import pl.tom.todo.app.entities.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramUserDetails implements UserDetails {

    private String password;
    private String username;
    private  String FirstName;
    private  String LastName;
    boolean enabled;
    private List<GrantedAuthority> authorities;

    public ProgramUserDetails(User user) {
        password=user.getPassword();
        username=user.getUsername();
        FirstName=user.getFirstName();
        LastName=user.getLastName();
        enabled= user.isEnabled();
        authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
