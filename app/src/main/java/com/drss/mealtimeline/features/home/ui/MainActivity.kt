package com.drss.mealtimeline.features.home.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drss.mealtimeline.R
import com.drss.mealtimeline.features.home.ui.composables.MealComposable
import com.drss.mealtimeline.features.home.ui.composables.NewMeal
import com.drss.mealtimeline.features.home.ui.theme.FomeSaciedadeTheme
import com.drss.mealtimeline.features.home.ui.viewmodel.MainScreenState
import com.drss.mealtimeline.features.home.ui.viewmodel.MainViewModel
import com.drss.mealtimeline.model.Meal

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            FomeSaciedadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main(vm)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealList(list: Map<String, List<Meal>>) {
    LazyColumn {
        list.forEach { (date, meals) ->
            stickyHeader {
                Row(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Surface(
                        Modifier
                            .height(24.dp)
                            .aspectRatio(1.0f)
                            .align(Alignment.CenterVertically)
                            .clip(CircleShape),
                        elevation = 8.dp
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar),
                            tint = MaterialTheme.colors.onSurface,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .height(18.dp)
                                .aspectRatio(1.0f)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Text(
                        text = date,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            if (list.keys.first() == date) {
                item {
                    NewMeal()
                }
            }
            items(meals) { meal ->
                MealComposable(meal = meal)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun Main(mainViewModel: MainViewModel) {
    val state by mainViewModel.state.observeAsState(MainScreenState.Loading)
    Column {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) }
        )
        StatelessMain(mainScreenState = state)
    }
}

@Composable
fun StatelessMain(mainScreenState: MainScreenState) {
    when (mainScreenState) {
        is MainScreenState.Data ->
            MealList(list = mainScreenState.groupedList)
        MainScreenState.Loading -> CircularProgressIndicator()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FomeSaciedadeTheme {
        StatelessMain(mainScreenState = MainScreenState.Loading)
    }
}