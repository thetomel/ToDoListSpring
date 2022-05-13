package pl.tom.todo.app.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.Services.TaskService;
import pl.tom.todo.app.Entities.Task;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/tasks")
@CrossOrigin //React can connect
public class TaskController {
    private final TaskService taskService;

    @Autowired //DI - Service
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    //
    //MAPPING SECTION
    //

    //GET
    @GetMapping //GET ALL
    public List<Task> getToDos() {
        return taskService.getToDos();
    }
    @GetMapping(path="{taskID}") //GET BY ID
    public Task getToDoByID(@PathVariable("taskID") Long taskID) {
        return taskService.getTask(taskID);
    }


    @PostMapping (path = "/{userId}")//POST NEW ToDo TO BASE
    public void addTask(@RequestBody Task tempToDo,@PathVariable("userId") Long userID){
        System.out.println(tempToDo);
        taskService.addTask(tempToDo);
        taskService.assignTask(tempToDo.getTaskID(), userID);
    }

    //DELETE
    @DeleteMapping(path="{taskID}") //DELETE BY ID HTTP
    public void delTask(@PathVariable("taskID") Long taskID){
        taskService.delTask(taskID);

    }
    //PUT
    @PutMapping(path="{taskID}") //PUT BY ID
    public void editContentTask(
            @PathVariable("taskID") Long taskID,
            @RequestParam(required = true)String text){ //Required.
        taskService.updateTaskContent(taskID, text);
    }


}
