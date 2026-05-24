package com.example.myapplication.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.data.model.Product

@Composable
fun ProductDetailScreen(
    product: Product,
    onBack: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(product) {
        viewModel.checkWishlist(product)
    }
    val isWishlisted by viewModel.isWishlisted.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(R.drawable.back_img),
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
            Text(
                text = product.name,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(android.R.drawable.ic_menu_search),
                    contentDescription = "search"
                )
            }
        }

        Image(
            painter = painterResource(product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Text(
            text = product.category,
            fontSize = 12.sp,
            color = Color(0xFF888888),
            modifier = Modifier.padding(start = 20.dp, top = 16.dp)
        )
        Text(
            text = product.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 4.dp)
        )
        Text(
            text = product.price,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 8.dp)
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 16.dp),
            color = Color(0xFFEEEEEE)
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(26.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 24.dp)
                .height(52.dp)
        ) {
            Text(text = "장바구니에 추가", color = Color.White, fontSize = 15.sp)
        }

        OutlinedButton(
            onClick = { viewModel.toggle(product) },
            shape = RoundedCornerShape(26.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp)
                .height(52.dp)
        ) {
            Text(
                text = if (isWishlisted) "위시리스트 ♥" else "위시리스트",
                color = Color.Black,
                fontSize = 15.sp
            )
        }
    }
}
