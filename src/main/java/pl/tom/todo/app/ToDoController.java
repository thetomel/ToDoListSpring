package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/")
@CrossOrigin //React can connect
public class ToDoController {
    private final ToDoService  toDoService;

    @Autowired //DI - Service
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }
    //
    //MAPPING SECTION
    //

    //GET
    @GetMapping //GET ALL
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }
    @GetMapping(path="{taskID}") //GET BY ID
    public Optional<ToDo> getToDoByID(@PathVariable("taskID") Long taskID) {
        return toDoService.getToDo(taskID);
    }

    //POST
    @PostMapping //POST NEW ToDo TO BASE
    public void addTask(@RequestBody ToDo tempToDo){
        System.out.println(tempToDo);
        toDoService.addTask(tempToDo);
    }
    //DELETE
    @DeleteMapping(path="{taskID}") //DELETE BY ID HTTP
    public void delTask(@PathVariable("taskID") Long taskID){
        toDoService.delTask(taskID);

    }
    //PUT
    @PutMapping(path="{taskID}") //PUT BY ID
    public void editContentTask(
            @PathVariable("taskID") Long taskID,
            @RequestParam(required = true)String text){ //Required.
        toDoService.updateTaskContent(taskID, text);
    }


}
