package com.vlascitchii.notes.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vlascitchii.notes.domain.input_validator.Validator
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.LocalWindowSizeClass
import com.vlascitchii.notes.presentation.screen.NotesScreen
import com.vlascitchii.notes.presentation.screen.NotesScreenViewModel
import com.vlascitchii.notes.presentation.screen.NotesScreenViewModelFactory
import com.vlascitchii.notes.presentation.ui.views.NoteDialog
import com.vlascitchii.notes.presentation.ui.views.NotesFloatingActionButton
import com.vlascitchii.notes.presentation.ui.views.NotesList
import com.vlascitchii.notes.utils.TagsNavigation

@Composable
fun NotesNavigation(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier.testTag(TagsNavigation.NAVIGATION)
) {
    Column(
        modifier = modifier.fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(navController = navHostController, startDestination = ScreenType.NotesList) {
            composable<ScreenType.NotesList> {
                val viewModel: NotesScreenViewModel = viewModel(
                    factory = NotesScreenViewModelFactory(Validator())
                )
                NotesScreen(
                    notesFloatingActionButton = {
                        NotesFloatingActionButton(
                            setDialogVisibilityState = { isVisible: Boolean -> viewModel.setDialogVisibilityState(isVisible) },
                            setCurrentNote = { note: Note -> viewModel.setCurrentNote(note) }
                        )
                    },
                    notesList = { innerPadding: PaddingValues ->
                        NotesList(
                            innerPadding = innerPadding,
                            setDialogVisibilityState = { isVisible: Boolean -> viewModel.setDialogVisibilityState(isVisible) },
                            setCurrentNote = { note: Note -> viewModel.setCurrentNote(note) },
                            windowWidth = LocalWindowSizeClass.current.widthSizeClass
                        )
                    },
                    noteDialog = {
                        NoteDialog(
                            showNoteDialogVisibility = viewModel.isDialogVisible,
                            setDialogVisibilityState = { isVisible: Boolean -> viewModel.setDialogVisibilityState(isVisible) },
                            currentNote = viewModel.currentNote,
                            changeNote = { newNote: Note ->
                                viewModel.changeNote(newNote)},
                            saveNote = { note: Note -> viewModel.saveNote(note) },
                            noteTitleValidator = { note: Note -> viewModel.validateNoteTitle(note.title) },
                            noteContentValidator = { note: Note -> viewModel.validateNoteContent(note.content) },
                        )
                    }
                )
            }
        }
    }
}
