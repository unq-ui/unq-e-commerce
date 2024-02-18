# UNQ » UIs » UNQEcommerce


UNQEcommerce es una plataforma donde los usuarios pueden cargar productos para la posterior compra y/o venta.

## Especificación de Dominio

### Dependencia

Agregar el repositorio:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Agregar la dependencia:

```xml
<dependency>
    <groupId>org.github.unqui</groupId>
    <artifactId>unq-e-commerce</artifactId>
    <version>1.0</version>
</dependency>
```

Toda interacción con el dominio se hace a través de la clase `EcommerceService`. La programación del dominio es provisto por la cátedra.

Se tiene que instanciar el sistema de la siguiente forma:

```kotlin
val ecommerceService = getEcommerceService()
```

### EcommerceService

```kotlin
val users: MutableList<User>

fun getAllCategories(): List<Category>

fun getAllAvailableProducts(): List<Product>

// @Throw EcommerceException si existe una categoria con el mismo nombre
fun addCategory(draftCategory: DraftCategory): Category

// @Throw EcommerceException si existe una categoria ya definida con el mismo nombre
fun modifyCategory(elementId: String, draftCategory: DraftCategory)

fun getAllCategories(): List<Category> = categories.toList()

// @Throw EcommerceException si existe un producto ya definido con el mismo nombre
fun addProduct(draftProduct: DraftProduct): Product 

// @Throw EcommerceException si el productId no existe
// @Throw EcommerceException si existe un producto ya definido con el mismo nombre
// @Throw EcommerceException si el usuario intenta modificar un producto que no le corresponde
fun modifyProduct(draftProduct: DraftProduct, productId: String)

// @Throw EcommerceException si el productId no existe
// @Throw EcommerceException si el usuario intenta eliminar un producto que no le corresponde
fun removeProduct(productId: String) 

fun getAllProducts(): List<Product> = products.toList()

// @Throw EcommerceException si no existe un producto con el id proporcionado
fun getProduct(productId: String): Product 

fun getProductsByCategory(categoryId: String): List<Product>

fun getProductsByUser(userId: String): List<Product>

// @Throw EcommerceException si no existe un producto con el id proporcionado o un usuario con el id proporcionado
// @Throw EcommerceException si el usuario compra uno de sus propios productos
fun purchaseProduct(userId: String, draftPurchase: DraftPurchase): User

// Devuelve las categorias que incluyan la phrase enviada o productos que tengan esa phrase en su nombre o descripcion, insensitivo.
fun search(phrase: List<Element>): List<Any>

// @Throw UserException si el username esta usado
fun addUser(drafUser: DraftUser): User

// @Throw UserException si id no existe
fun getUser(userId: String): User

```

### Product

```kotlin
class Product (
    val id: String,
    var name: String,
    var description: String,
    var image: String,
    var category: String,
    var price: Number
)
```

### Category

```kotlin
class Category(val id: String, var name: String)
```

### User

```kotlin
class User(
    val id: String,
    val username: String,
    val password: String,
    val image: String
)
```


### DraftCategory

Es la representación de una `Category` antes de ser guardada por el sistema

```kotlin
class DraftCategory(
    var name: String,
    var id: Number
)
```

### DraftProduct

Es la representación de un `Product` antes de ser guardada por el sistema

```kotlin
class DraftProduct(
    val name: String,
    val description: String,
    val image: String,
    val category: String,
    val price: Number
)
```

### DraftUser

Es la representación de un `User` antes de ser guardada por el sistema

```kotlin
class DraftUser(
    var username: String,
    var password: String,
    var image: String
)
```