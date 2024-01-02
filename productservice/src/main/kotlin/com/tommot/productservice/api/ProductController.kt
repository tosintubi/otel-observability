package com.tommot.productservice.api

import com.tommot.productservice.model.dto.ProductDto
import com.tommot.productservice.service.ProductService
import io.github.oshai.kotlinlogging.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v2/product-service")
class ProductController(private val productService: ProductService) {

    companion object : KLogging()

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    fun saveProduct(@RequestBody productDto: ProductDto): String {
        logger.info { "received request to save product: $productDto" }
        productService.saveProduct(productDto)
        logger.info { "saved product successfully" }
        return "OK"
    }
}