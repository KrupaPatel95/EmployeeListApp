package com.krupa.employeelistapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.krupa.employeelistapp.data.Employee
import com.krupa.employeelistapp.data.EmployeeListUIModel

@Composable
fun EmployeeListScreen(employeeList: EmployeeListUIModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Employee Directory") })
        }
    ) {
        Column(modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)) {
            if (employeeList.list.isNullOrEmpty()){
                Text(text = employeeList.errorMessage)
            } else {
                val emps = employeeList.list
                emps.let {
                    LazyColumn {
                        items(it){
                            EmployeeItemView(employee = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeItemView(employee: Employee){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(start = 20.dp, top = 20.dp, end = 20.dp)
        .shadow(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
        )
        .background(
            color = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = employee.photo_url_small,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "Employee Photo",
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = employee.full_name, style = MaterialTheme.typography.h6)
                Text(text = "ID: ${employee.uuid}", style = MaterialTheme.typography.body1)
                Text(text = "Phone: ${employee.phone_number}", style = MaterialTheme.typography.body1)
                Text(text = "Email: ${employee.email_address}", style = MaterialTheme.typography.body1)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EmployeeListScreenPreview(){
    EmployeeListScreen(employeeList = EmployeeListUIModel(
        list = listOf(
            Employee(
                uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
                full_name= "Justine Mason",
                phone_number =  "5553280123",
                email_address = "jmason.demo@squareup.com",
                biography = "Engineer on the Point of Sale team.",
                photo_url_small = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                photo_url_large = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
                team = "Point of Sale",
                employee_type = "FULL_TIME"
            )
        ),
    errorMessage = "")
    )
}