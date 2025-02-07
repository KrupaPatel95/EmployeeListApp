package com.krupa.employeelistapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.krupa.employeelistapp.data.Employee
import com.krupa.employeelistapp.data.EmployeeListUIModel
import com.krupa.employeelistapp.model.EmployeeViewModel
import com.krupa.employeelistapp.ui.theme.EmployeeListAppTheme
import com.krupa.employeelistapp.util.observeWithLifecycle
import com.krupa.employeelistapp.view.EmployeeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeViewModel by viewModels()
    private val uiState = mutableStateOf(EmployeeListUIModel())
    private fun updateUiState(uiModel: EmployeeListUIModel){
        uiState.value = uiModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchEmployees()
        setupViewModelObservers()
        setContent {
            EmployeeListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    EmployeeListScreen(uiState.value)
                }
            }
        }
    }

    fun setupViewModelObservers(){
        viewModel.employeesFlow.observeWithLifecycle(this){
            updateUiState(uiState.value.copy(list = it.map { it.copy() }))
        }

        viewModel.errorFlow.observeWithLifecycle(this) { message ->
            updateUiState(uiState.value.copy(errorMessage = message))
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}