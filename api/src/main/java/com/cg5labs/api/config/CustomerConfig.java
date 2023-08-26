package com.cg5labs.api.config;

import com.cg5labs.api.model.Customer;
import com.cg5labs.api.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            Customer michael = new Customer(
                    "Michael Michaelo",
                    32, "michael.michaelo@gmail.com",
                    LocalDate.of(1989, 02, 21)
            );
            Customer stranger = new Customer(
                    "Stranger Danger",
                    1, "stranger@gmail.com",
                    LocalDate.of(2020, 04, 30)
            );

            customerRepository.saveAll(List.of(michael, stranger));
        };
    }
}