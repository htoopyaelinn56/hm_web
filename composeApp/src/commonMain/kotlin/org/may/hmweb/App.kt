@file:OptIn(ExperimentalMaterial3Api::class)

package org.may.hmweb

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.materialkolor.dynamicColorScheme
import com.materialkolor.rememberDynamicColorScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    androidx.compose.material3.MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) MyTheme.DarkColorScheme else MyTheme.LightColorScheme,
    ) {
        Scaffold(
            backgroundColor = getContainerColor(),
            topBar = {
                TopAppBar(
                    backgroundColor = CardDefaults.elevatedCardColors().containerColor,
                    title = {
                        Text(
                            "H&M",
                            fontWeight = FontWeight.W200,
                            fontSize = 25.sp,
                            color = getItemColor(),
                        )
                    },
                    )
            },
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(padding).fillMaxSize()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp), // 2 columns
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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
}

@Composable
fun ItemCard(item: ItemData, onClick: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val rippleIndication = ripple(
        bounded = true,
        radius = 8.dp,
    )

    ElevatedCard(
        modifier = Modifier.size(150.dp, 220.dp)
            .clickable(interactionSource, indication = rippleIndication, onClick = onClick)
            .border(1.dp, getItemColor(), shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),


        ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
        ) {
            // Display an image
            SubcomposeAsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier.height(170.dp),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(
                    text = item.name,
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = getItemColor(),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                )
                Text(
                    text = "${formatWithCommas(item.price)} Ks",
                    overflow = TextOverflow.Ellipsis,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    color = getItemColor(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}