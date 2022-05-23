package pl.tom.todo.app.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tom.todo.app.security.LoginCredentials;

import java.util.Date;

@RestController
@RequestMapping(path ="/logon")
public class LoginController {
    @PostMapping(consumes={"application/json"}) //Getting JWT
        public String login(@RequestBody LoginCredentials loginCredentials){
        return Jwts.builder()
                .setSubject(loginCredentials.getUsername())
                .claim("roles","USER")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 1))
                .signWith(SignatureAlgorithm.HS256, "Secret")
                .compact();
    }

}
