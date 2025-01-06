package com.vlascitchii.notes.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.vlascitchii.notes.presentation.ui.views.NotesTopAppBar

@Composable
fun NotesScreen(
    notesFloatingActionButton: @Composable () -> Unit,
    notesList: @Composable (innerPadding: PaddingValues) -> Unit,
    noteDialog: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            NotesTopAppBar()
        },
        floatingActionButton = {
            notesFloatingActionButton.invoke()
        }
    ) { innerPadding ->
        notesList.invoke(innerPadding)
        noteDialog.invoke()
    }
}
