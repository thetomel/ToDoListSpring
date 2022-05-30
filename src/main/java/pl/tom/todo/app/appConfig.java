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

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Configuration
public class appConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository, UserRepository userRepository, CommentRepository commentRepository){
        return args -> {

            User User1 = new User("1","1");
            Task OneTD = new Task("Some task", User1);
            User User2 = new User("password", "password");
            User User3 = new User("user","user");
            Comment comment1 = new Comment("Ale fajna baza.", User2,OneTD);
            userRepository.saveAll(
                    List.of(User1, User2, User3)
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
