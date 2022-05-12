package pl.tom.todo.app.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.User;
import pl.tom.todo.app.UserService;

import java.util.List;

@RestController
@RequestMapping(path= "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Getters
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("{userid}")
    public User getUser(@PathVariable long userid){
        return userService.getUser(userid);
    }
    //Post
    @PostMapping
    public void postUser(@RequestBody User tempUser){
        System.out.println("adding "+tempUser);
        userService.postUser(tempUser);
    }
    //Put
    @PutMapping(path="/{UserID}/edit")
    public void updateUser(
            @PathVariable long UserId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String login
    ){
        userService.updateUser(UserId, name, login);
    }


    }

