@file:Suppress("SpellCheckingInspection")

package com.example.whale_test.features.information.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whale_test.R
import com.example.whale_test.ui.theme.PromotionCardBackground
import com.example.whale_test.ui.theme.Sf_ui

/*
 В макете ios дизайн, поэтому как замену UISegmentedControl использовал стандартный SingleChoiceSegmentedButtonRow
*/

data class InformationState(
    val list: List<Promotion>
)

@Composable
fun InformationScreen(
    state: InformationState,
    onSelectPromotion: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.header_img),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.information_text), fontFamily = Sf_ui,
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 68.dp)
                .padding(horizontal = 24.dp)
        )

        SegmentedBlok(state.list, onSelectPromotion)

    }
}

@Composable
fun SegmentedBlok(list: List<Promotion>, onSelectPromotion: (id: Long) -> Unit) {

    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 127.dp)
            .padding(horizontal = 24.dp)
    ) {

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            SegmentedButton(
                selected = selectedIndex == 0,
                onClick = { selectedIndex = 0 },
                shape = SegmentedButtonDefaults.itemShape(
                    index = 0,
                    count = 2
                ),
                icon = {}
            ) {
                Text(
                    text = stringResource(R.string.promotion_text),
                    fontFamily = Sf_ui,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                )
            }

            SegmentedButton(
                selected = selectedIndex == 1,
                onClick = { selectedIndex = 1 },
                shape = SegmentedButtonDefaults.itemShape(
                    index = 1,
                    count = 2
                ),
                icon = {}
            ) {
                Text(
                    text = stringResource(R.string.news_text),
                    fontFamily = Sf_ui,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
            }
        }

        when (selectedIndex) {
            0 -> PromotionBlock(list, onSelectPromotion, modifier = Modifier.padding(top = 20.dp))
        }
    }
}

@Composable
fun PromotionBlock(
    list: List<Promotion>,
    onSelectPromotion: (id: Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = list, key = { item -> item.id }) { item ->
            PromotionCard(
                item,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(color = PromotionCardBackground)
                    .clickable {
                        onSelectPromotion(item.id)
                    }
                    .padding(16.dp)

            )
        }
    }
}


@Composable
fun PromotionCard(
    item: Promotion,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        Image(
            painter = painterResource(item.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 80.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                item.text,
                fontFamily = Sf_ui,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                item.date.formatToUi(),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }
    }
}



