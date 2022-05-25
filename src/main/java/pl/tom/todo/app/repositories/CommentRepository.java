package pl.tom.todo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tom.todo.app.entities.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long>{
    List<Comment> findAllByAssignedToUser_UsernameEquals(String username);
}
