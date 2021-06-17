package com.example.pricesexercise;

import com.example.pricesexercise.core.Provider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PricesExerciseApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(PricesExerciseApplication.class, args);
        app.getBean("provider", Provider.class);
    }

}
