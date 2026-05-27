package com.example.myapplication.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.data.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "Wishlist")

class WishlistDataStore(private val context: Context) {

    private val gson = Gson()
    private val Wishlist_key = stringPreferencesKey("wishlist_key")

    val wishlistFlow: Flow<List<Product>> = context.dataStore.data.map { prefs ->
        val json = prefs[Wishlist_key] ?: "[]"
        val type = object : TypeToken<List<Product>>() {}.type
        gson.fromJson(json, type)
    }

    suspend fun saveWishlist(wishlist: List<Product>) {
        context.dataStore.edit { prefs ->
            prefs[Wishlist_key] = gson.toJson(wishlist)
        }
    }
}
