package org.may.hmweb

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun DetailPage(onPop: () -> Unit, itemData: ItemData?) {
    Scaffold(
        backgroundColor = getScaffoldBackgroundColor(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onPop) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = getScaffoldBackgroundColor(), title = {
                    Text(
                        "Detail",
                        fontWeight = FontWeight.W200,
                        fontSize = 25.sp,
                        color = getItemColor(),
                        textAlign = TextAlign.Center,
                    )
                }, elevation = 20.dp
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Product Image
                Card(
                    border = BorderStroke((1.5).dp, getItemColor()),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    SubcomposeAsyncImage(
                        model = itemData?.image,
                        contentDescription = null,
                        modifier = if (isMobileScreen()) Modifier.height(300.dp).fillMaxWidth()
                        else Modifier.size(
                            300.dp
                        ),
                        contentScale = ContentScale.Crop,
                    )
                }


                // Product Title
                Text(
                    text = itemData?.name ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = getItemColor()
                )

                // Product Price
                Text(
                    text = "${formatWithCommas(itemData?.price ?: 0f)} Ks",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = getItemColor()
                )

                // Product Description
                Text(
                    text = itemData?.description ?: "",
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    color = getItemColor()
                )
            }
        }
    }
}
