package org.github.unqui.model

class EcommerceService (private val products: MutableList<Product>, private val categories: MutableList<Category>, val users: MutableList<User>) {

    private val idGenerator = IdGenerator()

    // ABM Categories
    fun addCategory(draftCategory: DraftCategory): Category {
        if (categories.any { it.name == draftCategory.name }) throw EcommerceException("Category already defined")
        val element = Category(idGenerator.getElementId(), draftCategory.name)
        categories.add(element)
        return element
    }

    fun modifyCategory(elementId: String, draftCategory: DraftCategory) {
        val element = this.getCategory(elementId)
        if (categories.any { it.id != elementId && it.name == draftCategory.name }) throw EcommerceException("Category already defined")
        element.name = draftCategory.name
    }

    fun getAllCategories(): List<Category> = categories.toList()

    fun getCategory(categoryName: String): Category {
        return categories.find { it.name == categoryName } ?: throw EcommerceException("Category not found")
    }

    // ABM Products
    fun addProduct(draftProduct: DraftProduct): Product {
        val user = users.find { it.id === draftProduct.userId }
        if (user === null) throw UserException("User not found")
        if (products.any { it.name == draftProduct.name }) throw EcommerceException("Product already defined")
        val element = Product(
            idGenerator.getElementId(),
            draftProduct.name,
            draftProduct.description,
            draftProduct.image,
            draftProduct.categoryId,
            draftProduct.price,
            MinifiedUser(user.id, user.username, user.image)
        )
        products.add(element)
        return element
    }

    fun modifyProduct(draftProduct: DraftProduct, productId: String, userId: String): Product {
        val product = this.getProduct(productId)
        if (product === null) throw EcommerceException("Product not defined")
        if (product.user.id != userId) throw EcommerceException("You cannot modify this product")
        if (products.any { it.name == draftProduct.name }) throw EcommerceException("Already exists another product with this name")
        product.name = draftProduct.name
        product.description = draftProduct.description
        product.image = draftProduct.image
        product.categoryId = draftProduct.categoryId
        product.price = draftProduct.price
        return product
    }

    fun removeProduct(productId: String, userId: String) {
        val product = products.find{ it.id == productId } ?: throw EcommerceException("Product not found")
        if (product.user.id != userId) throw EcommerceException("You are not authorized to modify this product")
        products.remove(product)
    }

    fun getAllAvailableProducts(): List<Product> = products.toList()

    fun getProduct(productId: String): Product {
        return products.find { it.id == productId } ?: throw EcommerceException("Product not found")
    }
    fun getProductsByCategory(categoryId: String): List<Product> {
        return products.filter{ it.categoryId == categoryId }
    }

    fun getProductsByUser(userId: String): List<Product> {
        return products.filter{ it.user.id == userId }
    }

    // USERS
    fun addUser(newUser: DraftUser): User {
        if (users.any { it.username == newUser.username }) throw UserException("Username already defined")
        val user = User(idGenerator.getUserId(), newUser.username, newUser.password, newUser.image , mutableListOf())
        users.add(user)
        return user
    }

    fun getUser(userId: String): User {
        return users.find { it.id == userId } ?: throw UserException("User not found")
    }

    fun getMinifiedUser(userName : String): MinifiedUser {
        val user = users.find{it.username === userName }
        if (user === null) throw UserException("User not found")
        return MinifiedUser(user.id, user.username, user.image)
    }

    // SEARCH
    fun search(phrase: String): List<Any> {
        val searchPhrase = phrase.lowercase()
        val productsRes =  products.filter { it.description.lowercase().contains(searchPhrase) || it.name.lowercase().contains(searchPhrase) }
        return productsRes
    }

    fun purchaseProduct(purchase: Purchase) {
        val product = products.find{it.id === purchase.productId}
        products.remove(product)
    }

    fun login(username: String, password: String): User {
        return users.find { it.username == username && it.password === password } ?: throw UserException("Login Error")
    }
}
