package pl.tom.todo.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tom.todo.app.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}
