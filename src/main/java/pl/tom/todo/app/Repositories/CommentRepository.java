package pl.tom.todo.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tom.todo.app.Entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{
}
//