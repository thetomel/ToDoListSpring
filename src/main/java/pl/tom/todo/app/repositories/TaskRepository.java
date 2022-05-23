package pl.tom.todo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tom.todo.app.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
