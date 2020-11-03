package jacchm.footballapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballappApplication {
    public static void main(String[] args) {
        SpringApplication.run(FootballappApplication.class, args);
    }
}
