package pl.tom.todo.app.Services;

import antlr.BaseAST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tom.todo.app.Entities.User;
import pl.tom.todo.app.Repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository repository;
    private PasswordEncoder passwordEnocder;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEnocder = new BCryptPasswordEncoder();
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
    public void postUser(User toPostUser){
        String encodedPassword = this.passwordEnocder.encode(toPostUser.getPassword());
        boolean exits = repository.existsByUsername(toPostUser.getUsername());
        if(!exits){
            throw  new IllegalStateException("Such user exits!");
        }
        else {
            toPostUser.setPassword(encodedPassword);
            repository.save(toPostUser);
        }
    }

    public User getUser(long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalStateException("No such user"));
    }
    public void deleteUser(long userID){
        boolean exits = repository.existsById(userID);
        if(!exits){
            throw  new IllegalStateException("No such user with "+userID);
        }
        else repository.deleteById(userID);
    }
    @Transactional
    public void updateUser(long userId, String name, String login) {
        User user = repository.findById(userId).orElseThrow(()-> new IllegalStateException("No such user"));
//        if((name != null) && !Objects.equals(user.getName(),name)){
//            user.setName(name);
//        }
//
//        if((login != null) && !Objects.equals(user.getLogin(),login)){
//            user.setLogin(login);
//        }
    }
}
