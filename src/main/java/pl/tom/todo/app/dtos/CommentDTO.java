package pl.tom.todo.app.dtos;

import lombok.Data;
import pl.tom.todo.app.Entities.Comment;

@Data
public class CommentDTO {
    private String commentContent;

    public CommentDTO() {
    }

    public CommentDTO(String commentContent) {
        this.commentContent = commentContent;
    }

    public CommentDTO(Comment comment) {
        this.commentContent = comment.getCommentContent();
    }
}
