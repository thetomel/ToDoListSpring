package pl.tom.todo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tom.todo.app.CurrentUser;
import pl.tom.todo.app.dtos.TaskDTO;
import pl.tom.todo.app.entities.Task;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.repositories.TaskRepository;
import pl.tom.todo.app.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRespository;
    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRespository) {
        this.taskRepository = taskRepository;
        this.userRespository = userRespository;
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public List<Task> getAllTasksOfCurrentUser() {
        return taskRepository.findAllByAssignedTo_UsernameEquals(new CurrentUser().getCurrentUserName());
    }
    public void postTask(TaskDTO tempTask,String username){
        User user = (userRespository.findByUsername(username)).orElseThrow(()->new IllegalStateException("No such user "+ username));
        Task task = new Task(tempTask.getTaskName(), tempTask.getTaskDescription(), user);
        task.setDeadLine(tempTask.getDeadLine());
        task.setDone(tempTask.isDone());
        task.setLastUpdate(LocalDateTime.now());
        taskRepository.save(task);
        System.out.println("Adding "+ task);
    }
    @Transactional
    public void assignTask(Long taskId, Long userId) {
        User user = (userRespository.findById(userId)).orElseThrow(()->new IllegalStateException("No such user "+ userId));
        Task assignedTask = taskRepository.findById(taskId).orElseThrow(()->new IllegalStateException("No such task "+taskId));
        System.out.println("><><><"+user +" <><><> "+assignedTask);
        assignedTask.setAssignedTo(user);
    }
    public void delTask(Long taskID) {
        boolean exists =  taskRepository.existsById(taskID);
        if(!exists){
            throw new IllegalStateException("No such Task with ID:"+ taskID);
        }
        taskRepository.deleteById(taskID);
    }
    public void delTaskAsk(Long taskID) {
        boolean exists =  taskRepository.existsById(taskID);
        if(!exists){
            throw new IllegalStateException("No such Task with ID:"+ taskID);
        }
        boolean exists2 = taskRepository.findAllByAssignedTo_UsernameEquals(new CurrentUser().getCurrentUserName()).contains(taskRepository.existsById(taskID));
        if(!exists2)
        {
            taskRepository.deleteById(taskID);
        }
        throw new ArithmeticException("You don't have permission for this action");// IllegalStateException("No such Task with ID:"+ taskID);
    }
    @Transactional
    public void updateTaskContent(Long taskID, String taskContent){
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such task")); //Find Task or Throw info "it's null" or sth like that
        task.setTask(taskContent);
    }
    @Transactional
    public void updateTaskName(Long taskID, String newTaskName){
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such task")); //Find Task or Throw info "it's null" or sth like that
        task.setTaskName(newTaskName);
    }

    public Task getTask(Long taskID) { //Find Task by id
         return taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such Task"));
    }
    public void updateTask(TaskDTO temptask,long taskID) {
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such Task"));
        task.setTaskName(temptask.getTaskName());
        task.setTaskDescription(temptask.getTaskDescription());
        task.setLastUpdate(LocalDateTime.now());
        task.setDeadLine(temptask.getDeadLine());
        task.setDone(temptask.isDone());
        task.setPriority(temptask.getPriority());
        taskRepository.save(task);
    }
}
