package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class taskConfig {

    @Bean
    CommandLineRunner commandLineRunner(taskRepository repository, UserRepository userRepository){
        return args -> {
            task OneTD = new task("Some task");
            User User1 = new User("Name", "Login");
            repository.saveAll(
                    List.of(OneTD)
            );
            userRepository.saveAll(
                    List.of(User1)
            );
        };
    }
}
