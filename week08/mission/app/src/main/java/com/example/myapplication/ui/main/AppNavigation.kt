package com.example.myapplication.ui.main

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.R
import com.example.myapplication.data.model.Product
import com.example.myapplication.ui.bag.BagScreen
import com.example.myapplication.ui.buy.BuyScreen
import com.example.myapplication.ui.detail.ProductDetailScreen
import com.example.myapplication.ui.home.HomeScreen
import com.example.myapplication.ui.profile.ProfileScreen
import com.example.myapplication.ui.wishlist.WishlistScreen

sealed class BottomNavItem(val route: String, val label: String, val iconRes: Int) {
    object Home : BottomNavItem("home", "Home", R.drawable.housesimple)
    object Buy : BottomNavItem("buy", "Buy", R.drawable.listmagnifyingglass)
    object WishList : BottomNavItem("wishlist", "WishList", R.drawable.heartstraight)
    object Bag : BottomNavItem("bag", "Bag", R.drawable.bagsimple)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.user)
}

fun navigateToDetail(navController: NavController, product: Product) {
    val route = "detail/${product.image}/${Uri.encode(product.name)}/${Uri.encode(product.category)}/${Uri.encode(product.colors)}/${Uri.encode(product.price)}"
    navController.navigate(route)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Buy,
        BottomNavItem.WishList,
        BottomNavItem.Bag,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = bottomNavItems.any { currentRoute == it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    items = bottomNavItems,
                    currentRoute = currentRoute,
                    onItemSelected = { item ->
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(onProductClick = { navigateToDetail(navController, it) })
            }
            composable(BottomNavItem.Buy.route) {
                BuyScreen(onProductClick = { navigateToDetail(navController, it) })
            }
            composable(BottomNavItem.WishList.route) {
                WishlistScreen(onProductClick = { navigateToDetail(navController, it) })
            }
            composable(BottomNavItem.Bag.route) {
                BagScreen(
                    onOrderClick = {
                        navController.navigate(BottomNavItem.Buy.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = "detail/{image}/{name}/{category}/{colors}/{price}",
                arguments = listOf(
                    navArgument("image") { type = NavType.IntType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("category") { type = NavType.StringType },
                    navArgument("colors") { type = NavType.StringType },
                    navArgument("price") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val image = backStackEntry.arguments?.getInt("image") ?: 0
                val name = backStackEntry.arguments?.getString("name") ?: ""
                val category = backStackEntry.arguments?.getString("category") ?: ""
                val colors = backStackEntry.arguments?.getString("colors") ?: ""
                val price = backStackEntry.arguments?.getString("price") ?: ""
                ProductDetailScreen(
                    product = Product(image, name, category, colors, price),
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
