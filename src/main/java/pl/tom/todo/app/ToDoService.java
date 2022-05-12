package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final taskRepository taskRepository;
    @Autowired
    public ToDoService(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<task> getToDos() {
        return taskRepository.findAll();
    }
    public void addTask(task taskToAdd){
        taskRepository.save(taskToAdd);
        System.out.println("Adding "+ taskToAdd);
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
        task task = taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such task")); //Find Task or Throw info "it's null" or sth like that
        task.setTask(taskContent);
    }
    @Transactional
    public void updateTaskName(Long taskID, String newTaskName){
        task task = taskRepository.findById(taskID).orElseThrow(()-> new IllegalStateException("No such task")); //Find Task or Throw info "it's null" or sth like that
        task.setTaskName(newTaskName);
    }

    public Optional<task> getToDo(Long taskID) { //Find Task by id
        Optional<task> toReturn = taskRepository.findById(taskID);
                return toReturn;
    }
}
