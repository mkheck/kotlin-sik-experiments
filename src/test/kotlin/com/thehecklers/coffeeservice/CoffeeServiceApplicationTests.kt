package com.thehecklers.coffeeservice

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.returnResult
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.net.URI
import java.util.*

@RunWith(SpringRunner::class)
//@SpringBootTest
@SpringBootTest(classes = arrayOf(CoffeeServiceApplication::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoffeeServiceApplicationTests {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean
    lateinit var repo: CoffeeRepository

    val coffee1 = Coffee("0", "Tester's Choice")
    val coffee2 = Coffee("1", "Maxfail House")

    @Before
    fun setup() {
        Mockito.`when`(repo.findAll()).thenReturn(listOf(coffee1, coffee2))
        Mockito.`when`(repo.findByName(coffee1.name)).thenReturn(coffee1)
        Mockito.`when`(repo.findByName(coffee2.name)).thenReturn(coffee2)
        Mockito.`when`(repo.findById(coffee1.id!!)).thenReturn(Optional.of(coffee1))
        Mockito.`when`(repo.findById(coffee2.id!!)).thenReturn(Optional.of(coffee2))
    }

    @Test
    fun getAllCoffees() {
        val coffees = restTemplate.getForObject<Iterable<Coffee>>("/coffees")

        assert(coffees!!.count() > 0)
    }

    @Test
    fun getCoffeeById() {
        val coffee = restTemplate.getForEntity<Coffee>("/coffees/" + coffee1.id).body
        assert(coffee!!.equals(coffee1))
    }

    @Test
    fun getCoffeeByName() {
        val coffee = restTemplate.getForEntity<Coffee>("/coffees/search?name=" + coffee2.name).body
        assert(coffee!!.equals(coffee2))
    }
}
