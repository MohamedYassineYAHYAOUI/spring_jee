package fr.uge.jee.springmvc.reststudents;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
public class Application {

    @Bean
    public CommandLineRunner printStudents(){
        WebClient webClient = WebClient.create();
        return args -> {
            var students  = webClient.get()
                    .uri("http://localhost:8080/students")
                    .retrieve()
                    .bodyToMono(Student[].class)
                    .block();
            for (Student student : students) {
                System.out.println(student);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
