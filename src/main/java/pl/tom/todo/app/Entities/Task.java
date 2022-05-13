package pl.tom.todo.app.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks") //Create table
public class Task{
    @Id //Here Generate ID
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence"
    )
    private long taskID;
    private String taskName; //taskName
   // @Lob //TEXT no VARCHAR(255)
    private String taskDescription; //Task Description
    private byte priority = 0;

    //private long UserID;
    private boolean isDone = false;
    final private LocalDateTime uploadDate = LocalDateTime.now();
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private LocalDateTime deadLine = null;
    @JsonBackReference //Prevention from Looped JSON
    @ManyToOne()
    @JoinColumn(name = "user_ID")
    private User assignedTo;
//    @JsonManagedReference
    @OneToMany(mappedBy = "assignedToUser")
    private List<Comment> comments;

    public Task(String taskName) {
        this.taskName = taskName;
        this.taskDescription = taskName + " description";
        System.out.println("Upload" + uploadDate
        );

    }

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public Task(String taskName, User assignedTo) {
        this.taskName = taskName;
        this.taskDescription = taskName + " description";
        this.assignedTo = assignedTo;
    }

    public Task() {

    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public long getTaskID() {
        return taskID;
    }

    public void setTaskID(long taskID) {
        this.taskID = taskID;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public long getIdOfTask() {
        return taskID;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String task) {
        this.taskName = task;
    }
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "taskID=" + taskID +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", priority=" + priority +
                ", isDone=" + isDone +
                ", uploadDate=" + uploadDate +
                ", lastUpdate=" + lastUpdate +
                ", deadLine=" + deadLine +
                '}';
    }

    public void setTask(String taskContent) {
        taskDescription = taskContent;
    }
}