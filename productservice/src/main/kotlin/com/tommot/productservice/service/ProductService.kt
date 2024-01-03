package com.tommot.productservice.service

import com.tommot.productservice.model.dto.ProductDto
import com.tommot.productservice.model.dto.toProduct
import com.tommot.productservice.model.rto.ProductRto
import com.tommot.productservice.model.toProductRto
import com.tommot.productservice.model.toProductRtoList
import com.tommot.productservice.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun saveProduct(productDto: ProductDto): ProductRto{
        return productRepository.save(productDto.toProduct()).toProductRto()
    }

    fun getProducts(): List<ProductRto> {
        return productRepository.findAll().toProductRtoList()
    }
}
