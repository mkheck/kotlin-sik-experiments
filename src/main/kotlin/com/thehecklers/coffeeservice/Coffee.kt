package com.thehecklers.coffeeservice;

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Coffee(@Id val id: String? = null, val name: String = "Any old joe") {
    val code
            get() = name.toLowerCase().replace(" ", "-")

    override fun toString() = "Coffee{id=$id, name=$name, code=$code}"
}
