package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val products = listOf(
        Product(1, R.mipmap.product_1, "Air Jordan XXXVI", "", "", price = "US\$185"),
        Product(2, R.mipmap.product_2, "Nike Air Force 1 '07", "", "", price = "US\$115")
    )
}
