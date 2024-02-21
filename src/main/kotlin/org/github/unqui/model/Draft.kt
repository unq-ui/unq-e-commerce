package org.github.unqui.model

class DraftUser(
    var username: String,
    var password: String,
    var image: String
)

class DraftCategory(var name: String)

class DraftProduct(
    val name: String,
    val description: String,
    val image: String,
    val categoryId: String,
    val price: Number,
    val userId: String,
)
