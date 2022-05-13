package pl.tom.todo.app.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@ToString
@Table(name = "users")
public class User {
    @Id
    @Column(name="USER_ID" +
            "")
    @GeneratedValue()
    private long userID;
    private String name;
    @NotNull
    private String login;
    @JsonManagedReference //Prevention from Looped JSON
    @OneToMany(mappedBy = "assignedTo")
    private List<Task> Tasks;


    @JsonManagedReference
    @OneToMany(mappedBy = "assignedToUser")
    private List<Comment> comments;


    public User(String name) {
        this.name = name;
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

}
