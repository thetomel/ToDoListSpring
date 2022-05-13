package pl.tom.todo.app.RestControllers;

import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Entities.User;
import pl.tom.todo.app.Services.CommentService;

import java.util.List;

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
    @PostMapping(path = "task")
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
