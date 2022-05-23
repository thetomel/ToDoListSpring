package pl.tom.todo.app.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.services.TaskService;
import pl.tom.todo.app.entities.Task;
import pl.tom.todo.app.dtos.TaskDTO;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TaskDTO> getTasks() {
        return taskService.getToDos().stream().map(TaskDTO::new).collect(Collectors.toList());
    }
    @GetMapping(path="{taskID}") //GET BY ID
    public TaskDTO getTask(@PathVariable("taskID") Long taskID) {
        Task tempTask = taskService.getTask(taskID);
        return new TaskDTO(tempTask) ;
    }


//    @PostMapping (path = "/{userId}")//POST NEW ToDo TO BASE
//    public void addTask(@RequestBody Task tempToDo,@PathVariable("userId") Long userID){
//        System.out.println(tempToDo);
//        taskService.addTask(tempToDo);
//        taskService.assignTask(tempToDo.getTaskID(), userID);
//    }
    @PostMapping (path = "/new/{userId}")//POST NEW ToDo TO BASE
    public void postTask(@RequestBody TaskDTO taskDTO, @PathVariable("userId") Long userID){
        System.out.println(taskDTO);
        taskService.postTask(taskDTO, userID);
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
    @PatchMapping(path="/{taskID}/patch")
    public void patchTask(@PathVariable("taskID") Long taskID,
                          @RequestBody TaskDTO taskDTO){
        taskService.updateTask(taskDTO, taskID);
    }


}
