package com.example.qweather.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text as Text1
import com.example.qweather.ui.secreens.city.MainCityComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
    selectedTab: String,
    onDismissRequest: () -> Unit
) {

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Color.White
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            // Top bar: Back and Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text1(
                    text = "Back",
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .padding(end = 24.dp),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            RoundedCornerTabs(
                tabScreens = listOf(HomeTabItem.firstTab, HomeTabItem.secondTab),
                pagerState = pagerState,
                pagerContent = {
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    ) { page ->
                        when (page) {
                            0 -> MainCityComponent()
                            1 -> Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                LazyColumn {
                                    items(10) { index ->
                                        TabItem(
                                            label = "City $index",
                                            isSelected = selectedTab == "City $index",
                                            onClick = {

                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun TabItem(label: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp).background(
            color = if (isSelected) Color(0xFF70006B) else Color.White,
            shape = RoundedCornerShape(12.dp)
        )
    ) {
        Text1(text = label, color = if (isSelected) Color(0xFF70006B) else Color.White)
    }
}
