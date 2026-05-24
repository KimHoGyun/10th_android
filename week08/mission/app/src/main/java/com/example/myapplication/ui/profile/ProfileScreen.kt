package com.example.myapplication.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.model.UserData

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    val followingList by viewModel.followingList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = user?.avatar,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(
                text = user?.let { "${it.firstName} ${it.lastName}" } ?: "닉네임",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedButton(
                onClick = {},
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Text(text = "프로필 수정", color = Color.Black, fontSize = 14.sp)
            }
        }

        HorizontalDivider(color = Color(0xFFEEEEEE))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            listOf(
                Pair(R.drawable.bagsimple, "주문"),
                Pair(R.drawable.user, "패스"),
                Pair(R.drawable.heartstraight, "이벤트"),
                Pair(R.drawable.housesimple, "설정")
            ).forEachIndexed { index, (icon, label) ->
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = label,
                        modifier = Modifier.size(28.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = label,
                        fontSize = 11.sp,
                        color = Color(0xFF555555),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (index < 3) {
                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight(),
                        color = Color(0xFFEEEEEE)
                    )
                }
            }
        }

        HorizontalDivider(thickness = 8.dp, color = Color(0xFFF5F5F5))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "나이키 멤버 혜택",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "0개 사용 가능",
                    fontSize = 12.sp,
                    color = Color(0xFF888888),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Icon(
                painter = painterResource(R.drawable.back_img),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .rotate(180f),
                tint = Color.Unspecified
            )
        }

        HorizontalDivider(thickness = 8.dp, color = Color(0xFFF5F5F5))

        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "팔로잉 (${followingList.size})",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(text = "편집", fontSize = 13.sp, color = Color(0xFF888888))
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                items(followingList) { userData ->
                    FollowingItem(user = userData)
                }
            }
        }

        HorizontalDivider(thickness = 8.dp, color = Color(0xFFF5F5F5))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "회원 가입일: 2025년 9월",
                fontSize = 13.sp,
                color = Color(0xFF888888)
            )
        }
    }
}

@Composable
private fun FollowingItem(user: UserData) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = "${user.firstName} ${user.lastName}",
            fontSize = 11.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
