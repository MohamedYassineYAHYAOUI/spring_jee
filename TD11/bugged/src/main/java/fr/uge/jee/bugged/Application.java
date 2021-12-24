package fr.uge.jee.bugged;

import fr.uge.jee.bugged.entity.User;
import fr.uge.jee.bugged.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Collections;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
         new SpringApplication(Application.class).run(args);
    }

    @Bean
    DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:tcp://localhost/~/h2DBBugged");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    public CommandLineRunner cmd(UserRepository userRepository){
        return args -> {
            userRepository.save(new User("Arnaud", "Carayol", "0314159265"));
        };
    }
}
