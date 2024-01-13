package com.example.test_application.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_application.domain.entity.Task
import com.example.test_application.domain.repository.DomainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : ViewModel() {

    val task = MutableLiveData<Task>()

    fun getTaskById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            task.postValue(domainRepository.getTaskById(id))
        }
    }
}
