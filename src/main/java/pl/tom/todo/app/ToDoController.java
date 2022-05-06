package pl.tom.todo.app;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {
    private final ToDoService  toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @GetMapping
    public List<ToDo> getToDos() {
       return toDoService.getToDos();
    }
    @PostMapping
    public void addTask(@RequestBody ToDo tempToDo){
        System.out.println(tempToDo);
        toDoService.addTask(tempToDo);

    }

}
