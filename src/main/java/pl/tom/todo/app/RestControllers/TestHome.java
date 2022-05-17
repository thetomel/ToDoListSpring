package pl.tom.todo.app.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHome {
    @GetMapping("/")
    public String home(){
        return ("<h1>Working All Guys can see</h1>");
    }

    @GetMapping("/usertest")
    public String user(){
        return ("<h1>Working All USERS and admins sping can see</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Working only ADMINS can see</h1>");
    }
}
