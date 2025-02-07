package com.krupa.employeelistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.krupa.employeelistapp.MockUtil.mockEmployee
import com.krupa.employeelistapp.MockUtil.mockEmployeeList
import com.krupa.employeelistapp.data.DataResponse
import com.krupa.employeelistapp.data.Employee
import com.krupa.employeelistapp.model.EmployeeViewModel
import com.krupa.employeelistapp.model.NetworkResult
import com.krupa.employeelistapp.network.EmployeeRepository
import com.krupa.employeelistapp.network.EmployeeService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.kotlin.mock
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration
import kotlin.time.DurationUnit

@ExperimentalTime
@ExperimentalCoroutinesApi
class EmployeeListTest {

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var repo: EmployeeRepository
    private val service: EmployeeService = mock()

    @get:Rule
    val task = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repo = EmployeeRepository(service)
        viewModel = EmployeeViewModel(repo, coroutineRule.testDispatcher)
    }

    @After
    fun validate() {
        validateMockitoUsage() // Ensure error appears in the correct test method
    }

    @Test
    fun `fetch employees`() = runTest {
        val mockData = mockEmployeeList()
        Mockito.`when`(repo.getEmployee()).thenReturn(NetworkResult.Success(DataResponse(mockData)))
        viewModel.fetchEmployees()
        viewModel.employeesFlow.test(2.toDuration(DurationUnit.SECONDS)) {
            val expectItem = awaitItem()
            assertEquals(expectItem[0].full_name, mockEmployee().full_name)
            assertEquals(expectItem[0].phone_number, mockEmployee().phone_number)
            assertEquals(expectItem[0].photo_url_large, mockEmployee().photo_url_large)
            assertEquals(expectItem[0].team, mockEmployee().team)
            assertEquals(expectItem, mockData)
        }

    }

    @Test
    fun `fetch employees error`() = runTest {
        val mockData = arrayListOf<String>()
        Mockito.`when`(repo.getEmployee()).thenReturn(NetworkResult.Error(201,"error code"))
        viewModel.fetchEmployees()
        viewModel.errorFlow.test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals(mockData,emptyList<Employee>())
        }

    }

}