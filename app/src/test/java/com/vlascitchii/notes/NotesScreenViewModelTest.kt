package com.vlascitchii.notes

import com.vlascitchii.notes.domain.input_validator.Validator
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.screen.NotesScreenViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NotesScreenViewModelTest {

    private val viewModel: NotesScreenViewModel =
        NotesScreenViewModel(Validator())

    @Test
    fun `setDialogVisibilityState() changes isDialogVisible`() {
        viewModel.setDialogVisibilityState(true)
        assertTrue(viewModel.isDialogVisible.value)

        viewModel.setDialogVisibilityState(false)
        assertFalse(viewModel.isDialogVisible.value)
    }

    @Test
    fun `change note() changes current note`() {
        val newNote = Note("Test title", "Test content")
        viewModel.changeNote(newNote)

        assertEquals(viewModel.currentNote.value, newNote)
    }

    @Test
    fun `saveNote() saves notes`() {
        val note = Note("Test note", "Test content")
        viewModel.saveNote(note)
        assertEquals(viewModel.currentNote.value, note)
    }

    @Test
    fun `setCurrentNoe() changes the _currentNote in NotesScreenViewModel`() {
        val note = Note("Test note", "Test content")
        viewModel.setCurrentNote(note)
        assertEquals(viewModel.currentNote.value, note)
    }

    @Test
    fun `inputTitleValidator validates the input`() {
        val wrongInput = "w"
        val correctInput = "Test input~!@#$%^&*("

        assertTrue(viewModel.validateNoteTitle(wrongInput))
        assertFalse(viewModel.validateNoteTitle(correctInput))
    }

    @Test
    fun `inputContentValidator validates the input`() {
        val wrongInput = "w"
        val correctInput = "Test content~!@#$%^&*()"

        assertTrue(viewModel.validateNoteContent(wrongInput))
        assertFalse(viewModel.validateNoteContent(correctInput))
    }
}