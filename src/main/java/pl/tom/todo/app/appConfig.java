package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class appConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository, UserRepository userRepository){
        return args -> {

            User User1 = new User("Name", "Login");
            Task OneTD = new Task("Some task", User1);
            User User2 = new User("Name", "Login");
            User User3 = new User("Name", "Login");
            userRepository.saveAll(
                    List.of(User1, User2, User3)
            );
            repository.saveAll(
                    List.of(OneTD)
            );

        };
    }
}
