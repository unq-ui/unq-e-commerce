package org.github.unqui.model
import java.time.LocalDate

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

class Purchase(
    val productId: String,
    val card: CardInfo,
)

class CardInfo(
    val cardHolderName: String,
    val number: Number,
    val expirationDate: LocalDate,
    val cvv: Number
)
