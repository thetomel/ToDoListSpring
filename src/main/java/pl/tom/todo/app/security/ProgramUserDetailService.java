package pl.tom.todo.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.repositories.UserRepository;

import java.util.Optional;

@Service
public class ProgramUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User: "+username+" wasn't found"));
        return  user.map(ProgramUserDetails::new).get();
    }
}
