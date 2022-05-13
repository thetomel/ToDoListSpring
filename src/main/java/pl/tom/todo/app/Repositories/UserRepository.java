package pl.tom.todo.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tom.todo.app.Entities.User;

public interface UserRepository extends JpaRepository <User, Long> {
}
