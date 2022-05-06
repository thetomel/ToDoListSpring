package pl.tom.todo.app;


import javax.persistence.*;

@Entity
@Table(name = "ToDoID") //Create table
public class ToDo {
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
    private long id;
    private String task; //Temporary [0.1v]

    public ToDo(){}

    public ToDo(String task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                '}';
    }
}
