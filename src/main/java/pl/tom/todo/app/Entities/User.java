package pl.tom.todo.app.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(name="USER_ID" +
            "")
    @GeneratedValue()
    private long userID;
    @NotNull
    private String password;
    @Column(unique=true)
    private String username;
    private  String FirstName;
    private  String LastName;
    boolean enabled = false;
    private String roles ="USER";

//    @ElementCollection
//    private Set<String> roles = new HashSet<>();
    @JsonManagedReference //Prevention from Looped JSON
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> Tasks;


    @JsonManagedReference
    @OneToMany(mappedBy = "assignedToUser")
    private List<Comment> comments;

    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
        this.enabled = true;
    }
    public User(String password, String username, String roles) {
        this.password = password;
        this.username = username;
        this.enabled = true;
        this.roles = roles;
    }

}
