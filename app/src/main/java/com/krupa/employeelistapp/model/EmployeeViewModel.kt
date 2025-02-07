package com.krupa.employeelistapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krupa.employeelistapp.data.DataResponse
import com.krupa.employeelistapp.data.Employee
import com.krupa.employeelistapp.injections.DispatcherModule
import com.krupa.employeelistapp.network.EmployeeRepository
import com.krupa.employeelistapp.util.onError
import com.krupa.employeelistapp.util.onException
import com.krupa.employeelistapp.util.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel
@Inject constructor(
    private val employeeRepository: EmployeeRepository,
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _employeeList = MutableStateFlow<List<Employee>>(emptyList())
    val employeesFlow: Flow<List<Employee>> = _employeeList

    private val _error = MutableSharedFlow<String>()
    val errorFlow: Flow<String> = _error

    fun fetchEmployees() = viewModelScope.launch(ioDispatcher) {
        val response = employeeRepository.getEmployee()

        response.onSuccess {  list ->
            _employeeList.value = list.employees
        }.onError { code, message ->
            _error.emit("$code, $message")
        }.onException { t ->
            _error.emit("${t.message}")
        }
    }
}