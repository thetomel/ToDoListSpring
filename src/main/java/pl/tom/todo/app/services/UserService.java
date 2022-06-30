package pl.tom.todo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tom.todo.app.dtos.UserDTO;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.repositories.UserRepository;

import java.util.List;

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

//    public void postUser(User toPostUser){
//        String encodedPassword = this.passwordEnocder.encode(toPostUser.getPassword());
//        boolean exits = repository.existsByUsername(toPostUser.getUsername());
//        if(!exits){
//            throw  new IllegalStateException("Such user exits!");
//        }
//        else {
//            toPostUser.setPassword(encodedPassword);
//            repository.save(toPostUser);
//        }
//    }

    public User getUser(long id) {
        return repository.findById(id).orElseThrow(()-> new IllegalStateException("No such user"));
    }
    public User findUser(String username){
        return repository.findByUsername(username).orElseThrow(()-> new IllegalStateException("No such user"));
    }

    public void postUser(UserDTO tempUser) {
        User user = new User();
        if(repository.existsByUsername(tempUser.getUsername())&&(null!=tempUser.getUsername())){
            throw  new IllegalStateException("Name is already taken or ist empty.");
        }
        else user.setUsername(tempUser.getUsername());
       user.setFirstName(tempUser.getFirstName());
       user.setLastName(tempUser.getLastName());
//       user.setPassword(this.passwordEnocder.encode(tempUser.getPassword()));
       user.setPassword(tempUser.getPassword());
       user.setEnabled(tempUser.isEnabled());
        user.setRoles((tempUser.getRoles()==null)?"USER":tempUser.getRoles()) ;
       repository.save(user);
    }

    public void deleteUser(long userID){
        boolean exits = repository.existsById(userID);
        if(!exits){
            throw  new IllegalStateException("No such user with "+userID);
        }
        else {
            User user = repository.findById(userID).orElseThrow(()-> new IllegalStateException("No such user"));
            user.setComments(null);
            repository.deleteById(userID);
        }
    }
//    @Transactional
//    public void updateUser(long userId, String username, String password) {
//        User user = repository.findById(userId).orElseThrow(()-> new IllegalStateException("No such user"));
//        String encodedPassword = this.passwordEnocder.encode(user.getPassword());
//        if((null!=password)&&(!Objects.equals(password, user.getPassword()))){
//            user.setPassword(encodedPassword);
//        }
//        else throw  new IllegalStateException("Wrong Password - Same as last one or NULL!");
//        if((null!=username)&&(!Objects.equals(username, user.getUsername()))){
//            user.setUsername(username);
//        }
//        else throw  new IllegalStateException("Wrong Name!");
//        repository.save(user);
//
//        }
    @Transactional
    public void updateUser(long id, UserDTO tempUser){ // ID or username better?
        User user = repository.findById(id).orElseThrow(()-> new IllegalStateException("No such user"));

        if(repository.existsByUsername(tempUser.getUsername())&&(null!=tempUser.getUsername())){
            throw  new IllegalStateException("It's your actual username.");
        } else  user.setUsername(tempUser.getUsername());
        if(user.getPassword() == tempUser.getPassword()&&(null!=tempUser.getPassword())){
            throw  new IllegalStateException("It's your actual password or it's empty.");
        } else  user.setUsername(tempUser.getUsername());
        user.setFirstName(tempUser.getFirstName());
        user.setLastName(tempUser.getLastName());
        user.setPassword(tempUser.getPassword());
        user.setEnabled(tempUser.isEnabled());
        user.setRoles((tempUser.getRoles()==null)?"USER":tempUser.getRoles()) ;
        repository.save(user);
    }
    }


