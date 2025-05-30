package com.example.qweather.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.qweather.ui.theme.MyAppTheme
import kotlinx.coroutines.launch

sealed class HomeTabItem(
    val title: String,
) {
    data object firstTab : HomeTabItem(
        title = "WorldWide",
    )
    data object secondTab : HomeTabItem(
        title = "WorldWide",
    )

}

@Composable
fun RoundedCornerTabs(tabScreens: List<HomeTabItem>, pagerState: PagerState, pagerContent: @Composable () -> Unit = {}) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Tab Layout with Custom Indicator
        TabRow(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(vertical = 0.dp, horizontal = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MyAppTheme.colorScheme.background),
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            contentColor = MyAppTheme.colorScheme.tabContentColor,
            indicator = {

            },
            divider={}
        ) {
            tabScreens.forEachIndexed { index, tabItem ->
                HomeTab(
                    isTabSelected = pagerState.currentPage == index,
                    tabTitle = tabItem.title,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        pagerContent()
    }
}


@Composable
fun HomeTab(isTabSelected: Boolean,tabTitle: String,onClick: () -> Unit){
    Tab(
        modifier = if (!isTabSelected) Modifier.padding(bottom = 0.dp).fillMaxWidth().height(40.dp) else {
            Modifier
                .fillMaxWidth().height(40.dp).padding(4.dp).clip(RoundedCornerShape(12.dp))
                .background(
                    color = MyAppTheme.colorScheme.tabIndicatorBgColor,
                )
        },
        selected = isTabSelected,
        onClick = {
            onClick.invoke()
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = tabTitle,
                style = MyAppTheme.typography.tabTitle,
                color = if (isTabSelected) MyAppTheme.colorScheme.tabSelectedColor else MyAppTheme.colorScheme.tabUnSelectedColor
            )
        }
    }
}