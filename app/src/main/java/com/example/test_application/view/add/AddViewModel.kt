package com.example.test_application.view.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_application.domain.entity.Task
import com.example.test_application.domain.repository.DomainRepository
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
class AddViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : ViewModel() {

    val canFinish = MutableLiveData<Boolean>()

    var timeStart: Calendar = Calendar.getInstance()
    var timeFinish: Calendar = Calendar.getInstance()

    fun setTime(year: Int, month: Int, day: Int) {
        timeStart.year = year
        timeStart.month = month
        timeStart.day = day

        timeFinish.year = year
        timeFinish.month = month
        timeFinish.day = day
    }

    fun setTime(hour: Int, minute: Int, start: Boolean) {
        if (start) {
            timeStart.hour = hour
            timeStart.minute = minute
        } else {
            timeFinish.hour = hour
            timeFinish.minute = minute
        }
    }

    fun addTask(name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (timeStart <= timeFinish) {
                val task = Task(timeStart.timeInMillis, timeFinish.timeInMillis, name, description)
                domainRepository.insert(task)
                canFinish.postValue(true)
            } else {
                canFinish.postValue(false)
            }
        }
    }
}
