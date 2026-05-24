package com.example.myapplication.ui.wishlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.data.model.Product
import com.example.myapplication.ui.buy.ProductList

@Composable
fun WishlistScreen(
    onProductClick: (Product) -> Unit,
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val wishlist by viewModel.wishlist.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 8.dp)
        )
        ProductList(
            products = wishlist,
            isWishlisted = { viewModel.isWishlisted(it) },
            onWishlistToggle = { viewModel.toggle(it) },
            onItemClick = onProductClick
        )
    }
}
