package com.thehecklers.coffeeservice

import org.springframework.web.bind.annotation.*

import java.util.Optional

@RestController
@RequestMapping("/coffees")
class CoffeeController(private val repo: CoffeeRepository) {

    @GetMapping
    fun allCoffees() = repo.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) = repo.findById(id)

    @GetMapping("/search")
    fun searchForCoffee(@RequestParam name: String?) =
            if (name.isNullOrEmpty())
                repo.findAll().iterator().next()
            else
                repo.findByName(name)
}
