package pl.tom.todo.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue()
    private long id;
    private String name;
    private String login;
    @OneToMany(mappedBy = "user")
    private List<task> tasks;

    public User(String name) {
        this.name = name;
    }

    public User(String name, String login) {
        this.name = name;
        this.login = login;
    }

}
