package pl.tom.todo.app.dtos;

import lombok.Data;
import pl.tom.todo.app.entities.User;

@Data
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private String roles;
    private String password;
    //private List<Task> tasks;
//    private List<Comment> comments;

    public  UserDTO(User user){
        this.enabled=(user.isEnabled());
        this.firstName =user.getFirstName();
        this.lastName =user.getLastName();
        this.username =(user.getUsername());
        this.password =user.getPassword();
        this.roles=user.getRoles();
        //this.tasks=user.getTasks();
    }
    public UserDTO(){

    }
}
