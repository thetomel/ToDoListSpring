package pl.tom.todo.app.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.User;
import pl.tom.todo.app.UserService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(path= "/users")
public class userController {
    private final UserService userService;

    @Autowired
    public userController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping
    public void postUser(@RequestBody User tempUser){
        System.out.println("adding "+tempUser);
        userService.postUser(tempUser);
    }


    }

