package org.github.unqui.model

class Product (
    val id: String,
    var name: String,
    var description: String,
    var image: String,
    var category: String,
    var price: Number
)

class Category(val id: String, var name: String)

class MinifiedUser (
    val id: String,
    val username: String,
    val image: String
)

class User(
    val id: String,
    val username: String,
    val password: String,
    val image: String
)