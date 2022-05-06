package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {
    private final ToDoService  toDoService;

    @Autowired //DI - Service
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    @GetMapping //GET ALL
    public List<ToDo> getToDos() {
       return toDoService.getToDos();
    }
    @PostMapping //POST NEW ToDo TO BASE
    public void addTask(@RequestBody ToDo tempToDo){
        System.out.println(tempToDo);
        toDoService.addTask(tempToDo);
    }
    @DeleteMapping(path="{taskID}") //DELETE BY ID HTTP
    public void delTask(@PathVariable("taskID") Long taskID){
        toDoService.delTask(taskID);

    }
    @PutMapping(path="{taskID}") //PUT BY ID
    public void editContentTask(
            @PathVariable("taskID") Long taskID,
            @RequestParam(required = true)String text){ //Required.
        toDoService.updateTaskContent(taskID, text);
    }


}
