package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class taskConfig {

    @Bean
    CommandLineRunner commandLineRunner(ToDoRepository repository){
        return args -> {
            task OneTD = new task("Some task");
            repository.saveAll(
                    List.of(OneTD)
            );
        };
    }
}
