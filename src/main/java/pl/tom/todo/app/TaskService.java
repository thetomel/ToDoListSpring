package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRespository;




    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRespository) {
        this.taskRepository = taskRepository;
        this.userRespository = userRespository;
    }
    public List<Task> getToDos() {
        return taskRepository.findAll();
    }
    public void addTask(Task taskToAdd){
        taskRepository.save(taskToAdd);
        System.out.println("Adding "+ taskToAdd);
    }

    public void assignTask(Long taskId, Long userId) {
        User user = (userRespository.findById(userId)).orElseThrow(()->new IllegalStateException("No such user "+ userId));
        Task assignedTask = taskRepository.findById(taskId).orElseThrow(()->new IllegalStateException("No such task "+taskId));
        assignedTask.setAssignedTo(user);
    }

    public void delTask(Long taskID) {
        boolean exists =  taskRepository.existsById(taskID);
        if(!exists){
            throw new IllegalStateException("No such Task with ID:"+ taskID);
        }
        taskRepository.deleteById(taskID);
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

    public Optional<Task> getToDo(Long taskID) { //Find Task by id
        Optional<Task> toReturn = taskRepository.findById(taskID);
                return toReturn;
    }
}
