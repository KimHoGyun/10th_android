package com.example.myapplication.ui.buy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.myapplication.ui.wishlist.WishlistViewModel

@Composable
fun BuyScreen(
    onProductClick: (Product) -> Unit,
    viewModel: BuyViewModel = hiltViewModel(),
    wishlistViewModel: WishlistViewModel = hiltViewModel()
) {
    val tabs = listOf("전체", "Tops & T-Shirts", "Shoes")
    var selectedTab by remember { mutableIntStateOf(0) }
    val wishlist by wishlistViewModel.wishlist.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            contentColor = Color.Black
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTab == index) Color.Black else Color(0xFFAAAAAA)
                        )
                    }
                )
            }
        }

        when (selectedTab) {
            0 -> ProductGrid(
                products = viewModel.products,
                isWishlisted = { wishlistViewModel.isWishlisted(it) },
                onWishlistToggle = { wishlistViewModel.toggle(it) },
                onItemClick = onProductClick
            )
            1 -> Box(modifier = Modifier.fillMaxSize())
            2 -> Box(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    isWishlisted: (Product) -> Boolean,
    onWishlistToggle: (Product) -> Unit,
    onItemClick: (Product) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = products, key = { it.id }) { product ->
            ProductListItem(
                product = product,
                isWishlisted = isWishlisted(product),
                onWishlistToggle = { onWishlistToggle(product) },
                onClick = { onItemClick(product) }
            )
            HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 1.dp)
        }
    }
}

@Composable
fun ProductListItem(
    product: Product,
    isWishlisted: Boolean,
    onWishlistToggle: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(product.image),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFF5F5F5))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp)
        ) {
            if (product.isBestSeller) {
                Text(
                    text = "BestSeller",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6600)
                )
            }
            Text(
                text = product.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2
            )
            Text(
                text = product.category,
                fontSize = 12.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = product.colors,
                fontSize = 12.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 2.dp)
            )
            Text(
                text = product.price,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        IconButton(onClick = onWishlistToggle) {
            Icon(
                painter = painterResource(
                    if (isWishlisted) R.drawable.heartstraight_filled
                    else R.drawable.heartstraight
                ),
                contentDescription = "wishlist",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun ProductGrid(
    products: List<Product>,
    isWishlisted: (Product) -> Boolean,
    onWishlistToggle: (Product) -> Unit,
    onItemClick: (Product) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = products, key = { it.id }) { product ->
            ProductGridItem(
                product = product,
                isWishlisted = isWishlisted(product),
                onWishlistToggle = { onWishlistToggle(product) },
                onClick = { onItemClick(product) }
            )
        }
    }
}

@Composable
fun ProductGridItem(
    product: Product,
    isWishlisted: Boolean,
    onWishlistToggle: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Box {
            Image(
                painter = painterResource(product.image),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color(0xFFF5F5F5))
            )
            IconButton(
                onClick = onWishlistToggle,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(
                        if (isWishlisted) R.drawable.heartstraight_filled
                        else R.drawable.heartstraight
                    ),
                    contentDescription = "wishlist",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Column(modifier = Modifier.padding(top = 8.dp)) {
            if (product.isBestSeller) {
                Text(
                    text = "BestSeller",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6600)
                )
            }
            Text(
                text = product.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                lineHeight = 17.sp
            )
            Text(
                text = product.category,
                fontSize = 11.sp,
                color = Color(0xFF888888),
                modifier = Modifier.padding(top = 2.dp),
                maxLines = 1
            )
            Text(
                text = product.colors,
                fontSize = 11.sp,
                color = Color(0xFF888888)
            )
            Text(
                text = product.price,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
