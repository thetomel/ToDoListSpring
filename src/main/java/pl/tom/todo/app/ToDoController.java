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
    @GetMapping //GET ALL
    public List<ToDo> getToDos() {
       return toDoService.getToDos();
    }
    @PostMapping //POST
    public void addTask(@RequestBody ToDo tempToDo){
        System.out.println(tempToDo);
        toDoService.addTask(tempToDo);
    }
    @DeleteMapping(path="{taskID}") //DELETE HTTP
    public void delTask(@PathVariable("taskID") Long taskID){
        toDoService.delTask(taskID);

    }

}
