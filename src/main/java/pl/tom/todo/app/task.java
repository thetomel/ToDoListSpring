package pl.tom.todo.app;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tasks") //Create table
public class task {
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
    @Lob //TEXT no VARCHAR(255)
    private String taskDescription; //Task Description
    private byte priority = 0;

    //private long UserID;
    private boolean isDone = false;
    final private LocalDateTime uploadDate = LocalDateTime.now();
    private LocalDateTime lastUpdate = LocalDateTime.now();
    private LocalDateTime deadLine = null;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public task(String taskName) {
        this.taskName = taskName;
        this.taskDescription = taskName + " description";
        System.out.println("Upload" + uploadDate
        );

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

    public task() {

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