package org.may.hmweb

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailPage(onPop : () -> Unit, itemData: ItemData?){
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
    ){ padding ->
        Column {
            Text(text = "Item ID: ${itemData?.id}")
            Text(text = "Item Name: ${itemData?.name}")
            Text(text = "Description: ${itemData?.description}")
            Text(text = "Price: ${itemData?.price}")
        }
    }
}
