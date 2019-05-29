package com.thehecklers.coffeeservice

import org.springframework.data.repository.CrudRepository

interface CoffeeRepository : CrudRepository<Coffee, String> {
    fun findByName(name: String): Coffee
}
