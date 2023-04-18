package com.example.events.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.events.model.Event
import com.example.events.viewmodel.EventViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: EventViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("event_entry")}
            ) {
                Icon(Icons.Filled.Add,"")
            }
        },
        bottomBar = {},
        content = {
            it.calculateBottomPadding()
            Column() {
                TopAppBar(
                    title = { Text(text = "Events")},
                )
                LazyColumn (
                    modifier = Modifier.fillMaxWidth(),
                ){
                    items(items = viewModel.events.value) { event ->
                        EventItem(event = event)
                    }
                }
            }
        }
    )
}

@Composable
fun EventItem(event: Event) {
    Card(modifier = Modifier.fillMaxSize().padding(paddingValues = PaddingValues(8.dp))) {
        Column (modifier = Modifier.padding(PaddingValues(8.dp))){
            Text(text = "Title: ${event.title}")
            Text(text = "Category: ${event.category}")
            Text(text = "Date: ${event.date}")
            Text(text = "Description: ${event.description}")
            Text(text = "Location: ${event.location}")
        }
    }
}
