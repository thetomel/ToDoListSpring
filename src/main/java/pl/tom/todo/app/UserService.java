package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
    public void postUser(User toPostUser){
        repository.save(toPostUser);
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
        if((name != null) && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }

        if((login != null) && !Objects.equals(user.getLogin(),login)){
            user.setLogin(login);
        }
    }
}
