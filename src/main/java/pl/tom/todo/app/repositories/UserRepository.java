package pl.tom.todo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tom.todo.app.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}
