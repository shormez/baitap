package ntt.bai2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Bai2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bai2Application.class, args);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String newPassword = encoder.encode("padme");
//        System.out.println(newPassword);
    }

}
