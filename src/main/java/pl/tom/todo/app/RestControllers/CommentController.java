package pl.tom.todo.app.RestControllers;

import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Entities.Task;
import pl.tom.todo.app.Entities.User;
import pl.tom.todo.app.Services.CommentService;
import pl.tom.todo.app.dtos.CommentDTO;
import pl.tom.todo.app.dtos.TaskDTO;

import java.util.List;
import java.util.Optional;
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
    @PostMapping(path = "post/{taskID}/{userID}/")
    public void postComment(@RequestBody CommentDTO commentDTO,
                            @PathVariable Long taskID,
                            @PathVariable Long userID){
        System.out.println("adding "+commentDTO);
;        commentService.addComment(commentDTO, userID, taskID);
    }

   @DeleteMapping(path = "/-{commentID}")
    public void deleteUser(@PathVariable Long commentID){
        commentService.deleteComment(commentID);
    }

    @PatchMapping(path = "/{commentID}")
    public void editComment(@PathVariable Long commentID,
                        @RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentID, commentDTO.getCommentContent());
    }
}
