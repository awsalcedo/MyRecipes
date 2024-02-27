package com.asalcedo.myrecipes.util

import com.asalcedo.myrecipes.R

object Utilities {
    fun getImageId(image: String): Int {
        return when (image) {
            "spaghetti_carbonara" -> R.drawable.spaghetti_carbonara
            "chicken_tikka_masala" -> R.drawable.chicken_tikka_masala
            "margherita_pizza" -> R.drawable.margherita_pizza
            "caesar_salad" -> R.drawable.caesar_salad
            "chocolate_chip_cookies" -> R.drawable.chocolate_chip_cookies
            "guacamole" -> R.drawable.guacamole
            "sushi_rolls" -> R.drawable.sushi_rolls
            "beef_tacos" -> R.drawable.beef_tacos
            "beef_stir_fry" -> R.drawable.beef_stir_fry
            "chocolate_cake" -> R.drawable.chocolate_cake
            else -> R.drawable.spaghetti_carbonara
        }
    }
}