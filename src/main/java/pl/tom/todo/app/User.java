package pl.tom.todo.app;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue()
    private long userID;
    private String name;
    @NotNull
    private String login;

    @OneToMany(mappedBy = "users")
    private List<task> tasks;


    public User(String name) {
        this.name = name;
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

}
