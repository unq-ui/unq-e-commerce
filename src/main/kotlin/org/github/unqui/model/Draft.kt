package org.github.unqui.model

import java.time.LocalDate

class DraftUser(
    var username: String,
    var password: String,
    var image: String
)

class DraftCategory(
    var name: String,
    var id: Number
)

class DraftProduct(
    val name: String,
    val description: String,
    val image: String,
    val category: String,
    val price: Number
)
class DraftPurchase(
    val productId: String,
    val card: CardInfo,
)
class CardInfo(
    val cardHolderName: String,
    val number: Number,
    val expirationDate: LocalDate,
    val cvv: Number
)

