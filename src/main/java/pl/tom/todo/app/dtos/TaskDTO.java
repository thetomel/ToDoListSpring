package pl.tom.todo.app.dtos;

import lombok.Data;
import pl.tom.todo.app.entities.Task;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long taskID;
    private String taskName;
    private String userName;
    private String taskDescription;
    private int priority = 0;
    private boolean isDone = false;
    private LocalDateTime uploadDate = LocalDateTime.now();
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private LocalDateTime deadLine = null;

    public TaskDTO() {
    }
    public TaskDTO(Task task){
        this.taskID=task.getTaskID();
        this.taskName=task.getTaskName();
        this.taskDescription=task.getTaskDescription();
        this.priority=task.getPriority();
        this.isDone=task.isDone();
        this.uploadDate=task.getUploadDate();
        this.lastUpdate = LocalDateTime.now();
        this.deadLine = task.getDeadLine();
        UserDTO userDTO =new UserDTO(task.getAssignedTo());
        this.userName = (userDTO.getFirstName() + " " + userDTO.getLastName());
    }
}
