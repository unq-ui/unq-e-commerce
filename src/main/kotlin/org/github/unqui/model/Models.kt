package org.github.unqui.model

class Product (
    val id: String,
    var name: String,
    var description: String,
    var image: String,
    var categoryId: String,
    var price: Number,
    val user: MinifiedUser
)

class Category(val id: String, var name: String)
class User(
    val id: String,
    val username: String,
    val password: String,
    val image: String,
    val boughtProducts: MutableList<Product>
)

class MinifiedUser (
    val id: String,
    val username: String,
    val image: String
)
