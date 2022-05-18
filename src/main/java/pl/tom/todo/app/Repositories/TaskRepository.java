package pl.tom.todo.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tom.todo.app.Entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
