package com.krupa.employeelistapp.injections

import com.krupa.employeelistapp.network.EmployeeRepository
import com.krupa.employeelistapp.network.EmployeeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideEmployeeRepository(
        employeeService: EmployeeService
    ): EmployeeRepository {
        return EmployeeRepository(employeeService)
    }
}