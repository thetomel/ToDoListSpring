package pl.tom.todo.app;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CurrentUser {
    private final String username;
//    private String firstName;
//    private String lastName;
//    private boolean enabled;
//    private String roles;
//    private String password;
//    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    public CurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            this.username = ((UserDetails)principal).getUsername();
        } else {
            this.username = principal.toString();
        }
//        this.firstName = ((ProgramUserDetails)principal).getFirstName();
//        this.authorities = (Collection<SimpleGrantedAuthority>) ((UserDetails)principal).getAuthorities();
//        this.lastName = ((ProgramUserDetails)principal).getLastName();
    }
    public String getCurrentUserName(){
        return  username;
    }
}
