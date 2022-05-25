package pl.tom.todo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tom.todo.app.CurrentUser;
import pl.tom.todo.app.dtos.CommentDTO;
import pl.tom.todo.app.entities.Comment;
import pl.tom.todo.app.entities.Task;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.repositories.CommentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userRepository;
    private final TaskService taskRepository ;
    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userRepository, TaskService taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    public List<Comment> getComments(){
       return commentRepository.findAll();
    }
    public Optional<Comment> getComment(Long commentId){
        return  commentRepository.findById(commentId);
    }
    public void addCommentCurrent(CommentDTO commentDTO, String username, Long taskID){
        User user = userRepository.findUser(username);
        Task task = (taskRepository.getTask(taskID));
        Comment comment = new Comment(commentDTO.getCommentContent(), user, task);
        commentRepository.save(comment);
        System.out.println("Tried to save");
    }
    public void deleteCommentAdmin(Long commentID) {
        commentRepository.findById(commentID).orElseThrow(()-> new IllegalStateException("This comment dosen't exists. NO comment with ID:"+commentID));
        commentRepository.deleteById(commentID);
    }
    public void deleteComment(Long commentID){
        commentRepository.findById(commentID).orElseThrow(()-> new IllegalStateException("This comment dosen't exists. NO comment with ID:"+commentID));
        if(commentRepository.findAllByAssignedToUser_UsernameEquals(new CurrentUser().getCurrentUserName()).contains(commentRepository.findById(commentID))) {
            throw new IllegalStateException("No permission for this action");
        }
        else
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
