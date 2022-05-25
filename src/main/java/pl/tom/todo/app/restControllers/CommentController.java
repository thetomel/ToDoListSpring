package pl.tom.todo.app.restControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.CurrentUser;
import pl.tom.todo.app.entities.Comment;
import pl.tom.todo.app.services.CommentService;
import pl.tom.todo.app.dtos.CommentDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/comment")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<CommentDTO> getComments(){
        return commentService.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
    }
    @GetMapping(path="/{commentId}")
    public CommentDTO getComment(@PathVariable Long commentId){
        Comment tempComment = commentService.getComment(commentId).orElseThrow(()-> new IllegalStateException("No such comment"));
        return new CommentDTO(tempComment);
    }
    @PostMapping(path ="/post/{taskID}") //Change to /post in future
    public void postComment(@RequestBody CommentDTO commentDTO, @PathVariable Long taskID){
        commentService.addCommentCurrent(commentDTO,new CurrentUser().getCurrentUserName() ,taskID);
    }
    @DeleteMapping(path = "/my/{commentID}")
    public void deleteByUser(@PathVariable Long commentID) throws IllegalAccessException {
        commentService.deleteComment(commentID);
    }

    @DeleteMapping(path = "/admin/{commentID}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteByAdmin(@PathVariable Long commentID){
        commentService.deleteCommentAdmin(commentID);
    }
    @PatchMapping(path = "/{commentID}")
    public void editComment(@PathVariable Long commentID,
                        @RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentID, commentDTO.getCommentContent());
    }
}
