package pl.tom.todo.app.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.tom.todo.app.CurrentUser;
import pl.tom.todo.app.dtos.TaskDTO;
import pl.tom.todo.app.entities.Task;
import pl.tom.todo.app.services.TaskService;

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
    //GET
    @GetMapping //GET ALL
    public List<TaskDTO> getTasks() {
        return taskService.getAllTasks().stream().map(TaskDTO::new).collect(Collectors.toList());
    }
    @GetMapping(path ="/my")
    public List<TaskDTO> getTasksOfCurrentUser() {
        return taskService.getAllTasksOfCurrentUser().stream().map(TaskDTO::new).collect(Collectors.toList());
    }
    @GetMapping(path="{taskID}") //GET BY ID
    public TaskDTO getTask(@PathVariable("taskID") Long taskID) {
        Task tempTask = taskService.getTask(taskID);
        return new TaskDTO(tempTask) ;
    }
    @PostMapping (path = "/new")//POST NEW ToDo TO BASE
    public void postTask(@RequestBody TaskDTO taskDTO){
        System.out.println(taskDTO);
        String username = new CurrentUser().getCurrentUserName();
        taskService.postTask(taskDTO, username);
    }
    //DELETE
    @DeleteMapping(path="{taskID}") //DELETE BY ID HTTP
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delTask(@PathVariable("taskID") Long taskID){
        taskService.delTask(taskID);
    }
    @DeleteMapping(path="/my/{taskID}") //DELETE BY ID HTTP
    public void delTaskAsk(@PathVariable("taskID") Long taskID){
        taskService.delTaskAsk(taskID);
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
