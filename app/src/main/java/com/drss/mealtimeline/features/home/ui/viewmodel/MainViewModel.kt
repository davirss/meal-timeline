package com.drss.mealtimeline.features.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drss.mealtimeline.model.Meal
import com.drss.mealtimeline.model.Scale
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.*

class MainViewModel: ViewModel() {

    private val _state: MutableLiveData<MainScreenState> = MutableLiveData(MainScreenState.Loading)
    val state: LiveData<MainScreenState> = _state


    init {
        loadList()
    }

    private fun loadList() {
        val meal = Meal(
            LocalDateTime.now(), Scale.SATISFIED, Scale.SATISFIED, """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer massa eros, dictum non ullamcorper aliquam, ultrices vel arcu. Sed iaculis lacus id porttitor hendrerit. Curabitur porttitor magna et lectus cursus molestie. Donec vulputate dolor in sem accumsan ultricies. Proin leo ex, rutrum ac mi in, fermentum vulputate elit. In hac habitasse platea dictumst. Etiam a ex velit. Integer ac dolor urna. Cras faucibus viverra elit ut efficitur.
    """.trimIndent(), null
        )

        val list: MutableList<Meal> = mutableListOf()
        for (i in 0..10) {
            list.add(meal.copy(date = meal.date.plus(Duration.ofDays(i.toLong()))))
        }

        val map: MutableMap<String, List<Meal>> = mutableMapOf()
        list.forEach {
            val day = it.date.dayOfMonth
            val month = it.date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())
            val year = it.date.year
            val header = "$day de $month de $year"
            val meals = map.getOrPut(header) {
                listOf(it)
            }

            val test = meals.toMutableList()
            test.add(it)
            map[header] = test
        }

        _state.value = MainScreenState.Data(groupedList = map.toMap())

    }

}

sealed class MainScreenState {
    object Loading: MainScreenState()
    data class Data(val groupedList: Map<String, List<Meal>>): MainScreenState()
}