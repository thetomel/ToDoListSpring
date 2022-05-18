package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tom.todo.app.Entities.Comment;
import pl.tom.todo.app.Entities.Task;
import pl.tom.todo.app.Entities.User;
import pl.tom.todo.app.Repositories.CommentRepository;
import pl.tom.todo.app.Repositories.TaskRepository;
import pl.tom.todo.app.Repositories.UserRepository;

import java.util.List;

@Configuration
public class appConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository, UserRepository userRepository, CommentRepository commentRepository){
        return args -> {

            User User1 = new User("$2a$12$322Q75VJay4ee.mmsIwLWepPbLmiWJUAIIRKXj0tAUN.wi9U0jiIa", "Login","ADMIN");
            Task OneTD = new Task("Some task", User1);
            Comment comment1 = new Comment("Ale fajna baza.", User1,OneTD);
            userRepository.saveAll(
                    List.of(User1)
            );
            repository.saveAll(
                    List.of(OneTD)
            );
            commentRepository.saveAll(
                    List.of(comment1)
            );

        };
    }
}
