package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.Product
import kotlinx.coroutines.flow.StateFlow

interface WishlistRepository {
    val wishlist: StateFlow<List<Product>>
    suspend fun load()
    fun toggle(product: Product)
    fun isWishlisted(product: Product): Boolean
}
