package org.may.hmweb

data class ItemData(
    val id: String,
    val name: String,
    val price: Float,
    val description: String,
    val image: String,
)

val dummyItemList = List(50){
    ItemData(
        id = "${it + 1}",
        name = "Vaseline Gold",
        price = 1500000f,
        description = "Vaseline Gold is a premium moisturizer that provides deep hydration.",
        image = "https://raw.githubusercontent.com/htoopyaelinn56/hnm_product_images/refs/heads/main/images/Vaseline_Gold.png"
    )
}