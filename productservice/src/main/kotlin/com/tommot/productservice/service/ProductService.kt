package com.tommot.productservice.service

import com.tommot.productservice.model.Product
import com.tommot.productservice.model.dto.ProductDto
import com.tommot.productservice.model.dto.toProduct
import com.tommot.productservice.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun saveProduct(productDto: ProductDto): Product{

        return productRepository.save(productDto.toProduct())
    }
}
