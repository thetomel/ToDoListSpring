package pl.tom.todo.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Comments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    @Column(name="CommentID")
    private long commentID;
    private String commentContent;
//    @JsonBackReference //Prevention from Looped JSON
    @ManyToOne()
    @JoinColumn(name = "user_ID")
    private User assignedToUser;

//    @JsonBackReference //Prevention from Looped JSON
    @ManyToOne()
    @JoinColumn(name = "task_ID")
    private Task assignedToTask;

    public Comment(String commentContent) {
        this.commentContent = commentContent;
    }

    public Comment(String commentContent, User assignedToUser, Task assignedToTask) {
        this.commentContent = commentContent;
        this.assignedToUser = assignedToUser;
        this.assignedToTask = assignedToTask;
    }


}
