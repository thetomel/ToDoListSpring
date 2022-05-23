package pl.tom.todo.app.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.services.UserService;
import pl.tom.todo.app.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path= "/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Getters
    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getUsers().stream().map(UserDTO::new).collect(Collectors.toList());
    }
    @GetMapping("{userid}")
    public UserDTO getUser(@PathVariable long userid){
        User tempuser = userService.getUser(userid);
        return new UserDTO(tempuser);
    }
    //Post
    @PostMapping
    public void postUser(@RequestBody UserDTO tempUser){
        System.out.println("adding "+tempUser);
        userService.postUser(tempUser);
    }
    //Put
    @PutMapping(path="/{userID}/")
    public void updateUser(
            @PathVariable long userID,
            @RequestBody UserDTO tempUser)
    {
        userService.updateUser(userID, tempUser);
    }
    @DeleteMapping(path = "/{userID}")
    public void deleteUser(
            @PathVariable long userID){
        userService.deleteUser(userID);
    }
    }