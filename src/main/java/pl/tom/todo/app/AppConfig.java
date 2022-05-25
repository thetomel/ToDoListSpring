package pl.tom.todo.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.tom.todo.app.entities.Comment;
import pl.tom.todo.app.entities.Task;
import pl.tom.todo.app.entities.User;
import pl.tom.todo.app.repositories.CommentRepository;
import pl.tom.todo.app.repositories.TaskRepository;
import pl.tom.todo.app.repositories.UserRepository;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository, UserRepository userRepository, CommentRepository commentRepository){
        return args -> {

            User User1 = new User("$2a$12$322Q75VJay4ee.mmsIwLWepPbLmiWJUAIIRKXj0tAUN.wi9U0jiIa", "admin","ADMIN");
            User User2 = new User("$2a$12$sQHdPfBL/paku.6O6oJQ3.7Gu7K7v/WKDtGzAYOSjuFcUmtkod8j6","user", "USER");
            Task OneTD = new Task("Some task", User1);
            Task TwoTD = new Task("UserOfTwo", User2);
            Comment comment1 = new Comment("Ale fajna baza.", User1,OneTD);
            userRepository.saveAll(
                    List.of(User1, User2)
            );
            repository.saveAll(
                    List.of(OneTD, TwoTD)
            );
            commentRepository.saveAll(
                    List.of(comment1)
            );

        };
    }
}
