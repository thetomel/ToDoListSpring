package pl.tom.todo.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Repositories.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(){
       return commentRepository.findAll();
    }
    public void addComment(Comment comment){
        commentRepository.save(comment);
        System.out.println("Tried to save");
    }
}
