package com.example.test_application.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_application.domain.entity.Task
import com.example.test_application.domain.repository.DomainRepository
import com.example.test_application.utils.MILLS_IN_DAY
import com.example.test_application.utils.day
import com.example.test_application.utils.hour
import com.example.test_application.utils.minute
import com.example.test_application.utils.month
import com.example.test_application.utils.year
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : ViewModel() {

    val items = MutableLiveData<List<Task>>()

    fun getItemsByDate(year: Int, month: Int, day: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.year = year
        calendar.month = month
        calendar.day = day

        getItems(calendar)
    }

    fun getInitialItems() {
        getItems(Calendar.getInstance())
    }

    private fun getItems(calendar: Calendar) {
        calendar.hour = 0
        calendar.minute = 0
        val timeStart = calendar.timeInMillis
        val timeFinish = timeStart + MILLS_IN_DAY

        viewModelScope.launch(Dispatchers.IO) {
            val newItems = domainRepository.getTasksByDate(timeStart, timeFinish)
            if (newItems != items.value) items.postValue(newItems)
        }
    }
}
