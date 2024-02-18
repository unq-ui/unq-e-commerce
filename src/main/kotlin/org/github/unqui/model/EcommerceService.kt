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

    fun removeCategory(elementId: String) {
        categories.removeIf { it.id == elementId }
    }

    fun getAllCategories(): List<Category> = categories.toList()

    private fun getCategory(elementId: String): Category {
        return categories.find { it.id == elementId } ?: throw EcommerceException("Category not found")
    }

    // ABM Products
    fun addProduct(draftProduct: DraftProduct): Product {
        if (products.any { it.name == draftProduct.name }) throw EcommerceException("Product already defined")
        val element = Product(
            idGenerator.getElementId(),
            draftProduct.name,
            draftProduct.description,
            draftProduct.image,
            draftProduct.category,
            draftProduct.price
        )
        products.add(element)
        return element
    }

    fun modifyProduct(draftProduct: DraftProduct, productId: String) {
        val element = this.getProduct(productId)
        if (element === null) throw EcommerceException("Product not defined")
        element.name = draftProduct.name
        element.description = draftProduct.description
        element.image = draftProduct.image
        element.category = draftProduct.category
        element.price = draftProduct.price
    }

    fun removeProduct(productId: String) {
        products.removeIf { it.id == productId }
    }

    fun getAllAvailableProducts(): List<Product> = products.toList()

    fun getProduct(productId: String): Product {
        return products.find { it.id == productId } ?: throw EcommerceException("Product not found")
    }

    // USERS
    fun addUser(newUser: DraftUser): User {
        if (users.any { it.username == newUser.username }) throw UserException("Username already defined")
        val user = User(idGenerator.getUserId(), newUser.username, newUser.password, newUser.image , mutableListOf())
        users.add(user)
        return user
    }

    // SEARCH
    fun search(phrase: String): List<Any> {
        val search = phrase.lowercase()
        val productsRes =  products.filter { it.description.lowercase().contains(phrase) || it.name.lowercase().contains(phrase) }
        val categoriesRes = categories.filter { it.name.lowercase().contains(phrase)}
        return productsRes + categoriesRes
    }

    fun purchaseProduct(userId: String, draftPurchase: DraftPurchase): User {
        val user = users.find{it.id === userId}
        val product = products.find{it.id === draftPurchase.productId}
        if (user === null || product === null) throw EcommerceException("Los datos de compra son incorrectos")
        user.boughtProducts.add(product)
        products.remove(product)
        return user
    }
}
