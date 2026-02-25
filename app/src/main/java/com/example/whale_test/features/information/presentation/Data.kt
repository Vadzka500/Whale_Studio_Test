package com.example.whale_test.features.information.presentation

import com.example.whale_test.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getListPromotions(): List<Promotion> {
    return listOf(
        Promotion(
            id = 1L,
            image = R.drawable.img_promotion_1,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        ),
        Promotion(
            id = 2L,
            image = R.drawable.img_promotion_2,
            text = "Нам 10 лет повышаем скидку до 10% на всё!",
            date = LocalDate.now()
        ),
        Promotion(
            id = 3L,
            image = R.drawable.img_promotion_3,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        ),
        Promotion(
            id = 4L,
            image = R.drawable.img_promotion_4,
            text = "Нам 10 лет повышаем скидку до 10% на всё!",
            date = LocalDate.now()
        ),
        Promotion(
            id = 5L,
            image = R.drawable.img_promotion_5,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        ),
        Promotion(
            id = 6L,
            image = R.drawable.img_promotion_2,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        ),
        Promotion(
            id = 7L,
            image = R.drawable.img_promotion_1,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        ),
        Promotion(
            id = 8L,
            image = R.drawable.img_promotion_4,
            text = "Новые сорта крафта уже в наличии в магазинах",
            date = LocalDate.now()
        )
    )
}

data class Promotion(
    val id: Long,
    val image: Int,
    val text: String,
    val date: LocalDate
)

fun LocalDate.formatToUi(): String {
    return this
        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}
