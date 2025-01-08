package org.may.hmweb

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun HomePage(){
    Scaffold(
        backgroundColor = getScaffoldBackgroundColor(),
        topBar = {
            TopAppBar(
                backgroundColor = getScaffoldBackgroundColor(), title = {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "H&M",
                            fontWeight = FontWeight.W200,
                            fontSize = 25.sp,
                            color = getItemColor(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }, elevation = 20.dp
            )
        },
    ) { padding ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(padding).fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns =  if(isMobileScreen()) GridCells.Fixed(2)  else GridCells.Adaptive(230.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy( if(isMobileScreen())10.dp else  20.dp),
                horizontalArrangement = Arrangement.spacedBy( if(isMobileScreen()) 10.dp else 20.dp)
            ) {
                items(dummyItemList, key = { it.hashCode() }) {
                    ItemCard(it, onClick = {})
                }

                item {
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: ItemData, onClick: () -> Unit) {
    val offsetCardColor = remember { getRandomColor() }.copy(alpha = .6f)
    val interactionSource = remember { MutableInteractionSource() }

    val rippleIndication = androidx.compose.material.ripple(
        bounded = true,
        radius = 8.dp,
        color = offsetCardColor
    )


    // State to track hover status
    val isHovered: Boolean = interactionSource.collectIsHoveredAsState().value

    // Animate the offset based on hover state
    val offsetX by animateDpAsState(targetValue = if (isHovered) 16.dp else 0.dp)
    val offsetY by animateDpAsState(targetValue = if (isHovered) 16.dp else 0.dp)

    Box(
        modifier = Modifier.padding(if(isMobileScreen()) 10.dp else 20.dp).clickable(
            interactionSource, indication = rippleIndication, onClick = onClick
        ).hoverable(
            interactionSource = interactionSource,
            enabled = true
        )

    ) {
        // Back card
        Card(
            modifier = Modifier.offset(8.dp, 8.dp).size(240.dp,if(isMobileScreen()) 200.dp else 230.dp),
            colors = CardDefaults.cardColors().copy(containerColor = offsetCardColor),
            border = BorderStroke((1.5).dp, getItemColor()),
            shape = RoundedCornerShape(18.dp)
        ) {}

        // Front card
        Card(
            modifier = Modifier.offset(offsetX, offsetY).size(240.dp,if(isMobileScreen()) 200.dp else 230.dp),
            colors = CardDefaults.cardColors().copy(containerColor = getContainerColor()),
            border = BorderStroke((1.5).dp, getItemColor()),
            shape = RoundedCornerShape(14.dp)
        ) {
            // Icon
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SubcomposeAsyncImage(
                    model = item.image,
                    contentDescription = null,
                    modifier = Modifier.weight(1f),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Text(
                        text = item.name,
                        color = getItemColor(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                }
                Row {
                    Text(
                        text = "${formatWithCommas(item.price)} Ks",
                        color = getItemColor(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}