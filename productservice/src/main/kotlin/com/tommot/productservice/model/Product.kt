package com.tommot.productservice.model

import com.tommot.productservice.model.rto.ProductRto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productName: String? = null,
    val price: Double,
)
fun Product.toProductRto(): ProductRto =
    ProductRto(this.id!!, this.productName!!, this.price)