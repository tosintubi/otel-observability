package com.tommot.productservice.repository

import com.tommot.productservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}
