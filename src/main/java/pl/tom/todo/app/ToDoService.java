package pl.tom.todo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }
    public void addTask(ToDo taskToAdd){
        toDoRepository.save(taskToAdd);
        System.out.println("Adding "+ taskToAdd);
    }

    public void delTask(Long taskID) {
        boolean exists =  toDoRepository.existsById(taskID);
        if(!exists){
            throw new IllegalStateException("No such Task with ID:"+ taskID);
        }
        toDoRepository.deleteById(taskID);
    }
}
