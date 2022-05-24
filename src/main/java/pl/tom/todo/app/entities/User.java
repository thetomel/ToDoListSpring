package pl.tom.todo.app.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.tom.todo.app.security.ProgramUserDetails;

import javax.persistence.*;
import java.util.List;


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
//    @JsonManagedReference //Prevention from Looped JSON
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> Tasks;


//    @JsonManagedReference
    @OneToMany(mappedBy = "assignedToUser")
    private List<Comment> comments;

    public User() {
    }
    public User(ProgramUserDetails programUserDetails){
        this.username = programUserDetails.getUsername();
        this.password = programUserDetails.getPassword();
        this.FirstName = programUserDetails.getFirstName();
        this.LastName = programUserDetails.getLastName();
        this.roles =  programUserDetails.getAuthorities().toString().substring(1, programUserDetails.getAuthorities().toString().length() - 1);
        System.out.println(roles);
        this.enabled = programUserDetails.isEnabled();
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
