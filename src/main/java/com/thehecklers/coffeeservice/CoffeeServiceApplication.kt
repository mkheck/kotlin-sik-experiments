package com.thehecklers.coffeeservice

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

import java.util.Arrays

//import org.springframework.boot.runApplication

@SpringBootApplication
internal class CoffeeServiceApplication {
    @Bean
    fun demoData(repo: CoffeeRepository) = CommandLineRunner {
        repo.deleteAll()

        listOf("Kaldi's Coffee", "Espresso Roast", "Kona Coffee")
                .map { Coffee(name = it) }
                .forEach { repo.save(it) }

        repo.findAll().forEach { println(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<CoffeeServiceApplication>(*args)
}

//fun main(args: Array<String>) {
//	runApplication<CoffeeServiceApplication>(*args)
//}
