package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToDoConfig {

    @Bean
    CommandLineRunner commandLineRunner(ToDoRepository repository){
        return args -> {
            ToDo OneTD = new ToDo("Some task");
            repository.saveAll(
                    List.of(OneTD)
            );
        };
    }
}
