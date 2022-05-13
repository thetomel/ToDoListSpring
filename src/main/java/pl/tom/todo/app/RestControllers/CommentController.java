package pl.tom.todo.app.RestControllers;

import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Services.CommentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/no")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }
    @GetMapping(path="/{commentId}")
    public Optional<Comment> getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }
    @PostMapping(path = "post/{userId}/{taskId}")
    public void postCommentID(@RequestBody Comment comment,
                           @PathVariable Long userId, @PathVariable Long taskId) { //RequestParm? would be better?
        comment.setAssignedToUser(commentService.findUser(userId));
        comment.setAssignedToTask(commentService.findTask(taskId));
        System.out.println("adding");
        commentService.addComment(comment);
    }
        @PostMapping(path = "post")
        public void postComment(@RequestBody Comment comment){
            System.out.println("adding "+comment);
            commentService.addComment(comment);
    }
   @DeleteMapping(path = "/del-{commentID}")
    public void deleteUser(@PathVariable Long commentID){
        commentService.deleteComment(commentID);
    }
    @PatchMapping(path = "/comment/{commentID}")
    public void editComment(@PathVariable Long commentID,
                            @RequestParam String text){
        commentService.updateComment(commentID, text);
    }
//    @PostMapping(path = "task/{task}/{userID}")
//    public void postComment(@PathVariable Long userID,@RequestParam String text, @PathVariable Long task){
//
//        Comment comment = new Comment(text);
//        System.out.println("adding "+comment);
//        commentService.addComment(comment);
//    }
}
