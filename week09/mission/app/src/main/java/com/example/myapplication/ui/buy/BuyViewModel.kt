package com.example.myapplication.ui.buy

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor() : ViewModel() {
    val products = listOf(
        Product(R.mipmap.product_1, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", "5 Colours", "US\$10"),
        Product(R.mipmap.product_2, "Nike Elite Crew", "Basketball Socks", "7 Colours", "US\$16"),
        Product(R.mipmap.product_3, "Nike Air Force 1 '07", "Women's Shoes", "5 Colours", "US\$115", isBestSeller = true),
        Product(R.mipmap.product_4, "Jordan ENike Air Force 1 '07essentials", "Men's Shoes", "2 Colours", "US\$115", isBestSeller = true)
    )
}
