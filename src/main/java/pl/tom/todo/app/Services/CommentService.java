package pl.tom.todo.app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Entities.Task;
import pl.tom.todo.app.Entities.User;
import pl.tom.todo.app.Repositories.CommentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userRespository;
    private final TaskService taskRepository ;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userRespository,TaskService taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRespository = userRespository;
    }

    public List<Comment> getComments(){
       return commentRepository.findAll();
    }
    public Optional<Comment> getComment(Long commentId){
        return  commentRepository.findById(commentId);
    }
    public void addComment(Comment comment){
        commentRepository.save(comment);
        System.out.println("Tried to save");
    }
    public User findUser(Long userId){ //it shouldn't be here.
        return userRespository.getUser(userId);
    }public Task findTask(Long userId){ //it shouldn't be here
        return taskRepository.getTask(userId);
    }
    public void deleteComment(Long commentID) {
        commentRepository.findById(commentID).orElseThrow(()-> new IllegalStateException("This comment dosen't exists. NO comment with ID:"+commentID));
        commentRepository.deleteById(commentID);
    }
    @Transactional
    public void updateComment(Long commentID,String content) {
        Comment comment = commentRepository.findById(commentID).orElseThrow(()-> new IllegalStateException("This comment dosen't exists. NO comment with ID:"+commentID));
        if((content !=null)&&(content.length()>0)&&(!Objects.equals(content, comment.getCommentContent()))){
            comment.setCommentContent(content);
        }
    }
}
