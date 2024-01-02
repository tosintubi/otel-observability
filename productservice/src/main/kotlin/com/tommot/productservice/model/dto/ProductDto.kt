package com.tommot.productservice.model.dto

import com.tommot.productservice.model.Product

data class ProductDto(
    val id: Long? = null,
    val productName: String? = null,
    val price: Double) {
}

fun ProductDto.toProduct(): Product{
    return Product(
        productName = this.productName,
        price = this.price
    )
}