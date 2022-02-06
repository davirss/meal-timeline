package com.drss.nutricao.features.home.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drss.nutricao.R
import com.drss.nutricao.features.home.ui.theme.CustomShape
import com.drss.nutricao.features.home.ui.theme.FomeSaciedadeTheme
import com.drss.nutricao.model.Meal
import com.drss.nutricao.model.Scale
import java.time.Instant
import java.time.LocalDateTime

@Composable
fun MealComposable(meal: Meal) {
    Box(
        modifier = Modifier
            .padding(start = 0.dp, end = 8.dp, top = 8.dp)
            .height(IntrinsicSize.Max)
            .background(MaterialTheme.colors.surface)
    ) {
        Column {
            Row {
                TextWithDrawableStart(
                    meal.date.toString(),
                    painterResource(id = R.drawable.ic_clock),
                    Modifier
                        .padding(start = 4.dp)
                )
                Divider(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 8.dp, end = 8.dp)
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                TimelineLeftDivider()
                Column(modifier = Modifier.padding(top = 8.dp)) {
                   MealContent(meal = meal)
                }
            }
        }
    }
}

@Composable
fun NewMeal() {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        TimelineLeftDivider(modifier = Modifier.fillMaxHeight())
        OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "New entry")
        }
    }
}

@Composable
fun TextWithDrawableStart(text: String, painter: Painter, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painter = painter,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null,
            modifier = Modifier
                .height(16.dp)
                .width(16.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun TimelineLeftDivider(modifier: Modifier = Modifier) {
    Divider(
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
            .fillMaxHeight()
            .padding(start = 11.dp, end = 11.dp)
            .width(1.dp)
            .fillMaxHeight()
    )
}

@Composable
fun SmallTitleContent(title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit ) {
    Column(modifier = modifier) {
        Text(text = title, modifier = Modifier.padding(bottom = 4.dp), style = MaterialTheme.typography.subtitle2)
        content()
    }
}

@Composable
fun MealContent(meal: Meal) {
    val contentModifier = Modifier
//                    if (meal.image != null) {
    Image(
        painter = painterResource(id = R.drawable.test),
        contentDescription = "Meal image",
        modifier = contentModifier
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colors.primary)
            .width(IntrinsicSize.Max)
            .aspectRatio(1.58f)
    )
//                    }
    if (meal.description != null) {

        val textStyle = meal.image?.let {
            MaterialTheme.typography.caption
        } ?: MaterialTheme.typography.body2

        Text(
            text = meal.description,
            modifier = contentModifier
                .background(MaterialTheme.colors.surface)
                .padding(bottom = 8.dp, top = 8.dp),
            color = MaterialTheme.colors.onSurface,
            style = textStyle
        )
    }
    StomachFeeling("Sensação antes da refeição", meal.hunger, R.drawable.ic_no_food)
    StomachFeeling("Sensação depois da refeição", meal.hunger, R.drawable.ic_face)
}

@Composable
fun StomachFeeling(title: String, hunger: Scale, icon: Int) {
    val mealText = when (hunger) {
        Scale.STARVING -> R.string.scale_starving
        Scale.VERY_HUNGRY -> R.string.scale_very_hungry
        Scale.HUNGRY -> R.string.scale_hungry
        Scale.SATISFIED -> R.string.scale_satiated
        Scale.MILDLY_FULL -> R.string.scale_slightly_full
        Scale.FULL -> R.string.scale_full
        Scale.STUFFED -> R.string.scale_stuffed
    }

    SmallTitleContent(title = title, modifier = Modifier.padding(bottom = 8.dp)) {
        TextWithDrawableStart(
            stringResource(mealText),
            painterResource(id = icon)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val meal = Meal(
        LocalDateTime.now(), Scale.SATISFIED, Scale.SATISFIED, """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer massa eros, dictum non ullamcorper aliquam, ultrices vel arcu. Sed iaculis lacus id porttitor hendrerit. Curabitur porttitor magna et lectus cursus molestie. Donec vulputate dolor in sem accumsan ultricies. Proin leo ex, rutrum ac mi in, fermentum vulputate elit. In hac habitasse platea dictumst. Etiam a ex velit. Integer ac dolor urna. Cras faucibus viverra elit ut efficitur.
    """.trimIndent(), null
    )
    FomeSaciedadeTheme {
        MealComposable(meal)
    }
}

@Preview(showBackground = true)
@Composable
fun NewPreview() {
    FomeSaciedadeTheme {
        NewMeal()
    }
}

