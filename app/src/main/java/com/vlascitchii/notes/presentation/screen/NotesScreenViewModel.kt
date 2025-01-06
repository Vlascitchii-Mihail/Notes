package com.vlascitchii.notes.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vlascitchii.notes.domain.input_validator.Validator
import com.vlascitchii.notes.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NotesScreenViewModel(
    private val validator: Validator
): ViewModel() {

    private var _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible: StateFlow<Boolean> = _isDialogVisible

    private var _currentNote = MutableStateFlow(Note())
    val currentNote: StateFlow<Note> = _currentNote

    fun setDialogVisibilityState(isVisible: Boolean) {
        _isDialogVisible.value = isVisible
    }

    fun changeNote(newNote: Note) {
        _currentNote.value = newNote
    }

    //TODO: save the note in the files
    fun saveNote(note: Note) {
        _currentNote.value = note
    }

    fun setCurrentNote(note: Note) {
        _currentNote.value = note
    }

    fun validateNoteTitle(title: String): Boolean = validator.validateTitleInput(title)

    fun validateNoteContent(content: String): Boolean = validator.validateContentInput(content)
}

class NotesScreenViewModelFactory(
    private val validator: Validator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesScreenViewModel::class.java)) {
            return NotesScreenViewModel(validator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}