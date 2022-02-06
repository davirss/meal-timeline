package com.drss.mealtimeline.model

import android.net.Uri
import java.time.LocalDateTime

enum class Scale {
    STARVING,
    VERY_HUNGRY,
    HUNGRY,
    SATISFIED,
    MILDLY_FULL,
    FULL,
    STUFFED
}

data class Meal(
    val date: LocalDateTime,
    val hunger: Scale,
    val satiation: Scale,
    val description: String?,
    val image: Uri?
) {
    init {
        require(description != null || image != null) {
            "Either description or image should be filled."
        }
    }
}