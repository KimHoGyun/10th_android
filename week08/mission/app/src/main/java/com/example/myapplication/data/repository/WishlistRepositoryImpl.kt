package com.example.myapplication.data.repository

import com.example.myapplication.data.local.WishlistDataStore
import com.example.myapplication.data.model.Product
import com.example.myapplication.domain.repository.WishlistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WishlistRepositoryImpl @Inject constructor(
    private val dataStore: WishlistDataStore
) : WishlistRepository {

    private val _wishlist = MutableStateFlow<List<Product>>(emptyList())
    override val wishlist: StateFlow<List<Product>> = _wishlist.asStateFlow()

    override suspend fun load() {
        _wishlist.value = dataStore.wishlistFlow.first()
    }

    override fun toggle(product: Product) {
        val current = _wishlist.value.toMutableList()
        val existing = current.find { it.name == product.name }
        if (existing != null) {
            current.remove(existing)
        } else {
            current.add(product.copy(isWishlisted = true))
        }
        _wishlist.value = current
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.saveWishlist(_wishlist.value)
        }
    }

    override fun isWishlisted(product: Product): Boolean =
        _wishlist.value.any { it.name == product.name }
}
