package com.drss.nutricao.features.home.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val CustomShape = Shapes(
    small = RoundedCornerShape(0.dp, 4.dp, 0.dp, 4.dp),
    medium = RoundedCornerShape(0.dp, 4.dp, 0.dp, 4.dp),
    large = RoundedCornerShape(0.dp, 4.dp, 0.dp, 4.dp),
)