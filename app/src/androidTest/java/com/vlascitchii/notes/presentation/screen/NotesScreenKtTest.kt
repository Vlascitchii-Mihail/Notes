package com.vlascitchii.notes.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vlascitchii.notes.R
import com.vlascitchii.notes.domain.input_validator.Validator
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.LocalWindowSizeClass
import com.vlascitchii.notes.presentation.ui.views.NoteDialog
import com.vlascitchii.notes.presentation.ui.views.NotesFloatingActionButton
import com.vlascitchii.notes.presentation.ui.views.NotesList
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val viewModel = NotesScreenViewModel(Validator())
    private lateinit var floatingActionButtonDesc: String
    private lateinit var dialogDescription: String
    private lateinit var noteDialogTitleInputDescr: String
    private lateinit var noteDialogContentInputDescr: String
    private lateinit var dialogSaveButtonDescr: String
    private lateinit var dialogDeleteButtonDescr: String
    private lateinit var errorTitleMsg: String
    private lateinit var errorContentMsg: String
    private lateinit var errorMsgDescr: String

    @Before
    fun initNoteScreen() {
        composeTestRule.setContent {
            floatingActionButtonDesc = stringResource(R.string.add_new_note_description)
            dialogDescription = stringResource(R.string.dialog_description)
            noteDialogTitleInputDescr = stringResource(R.string.note_dialog_title_input)
            noteDialogContentInputDescr = stringResource(R.string.note_dialog_content_input)
            dialogSaveButtonDescr = stringResource(R.string.save)
            dialogDeleteButtonDescr = stringResource(R.string.delete)
            errorTitleMsg = stringResource(R.string.error_msg_title)
            errorContentMsg = stringResource(R.string.error_msg_content)
            errorMsgDescr = stringResource(R.string.error_msg_description)

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
                        windowWidth = WindowWidthSizeClass.Compact
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

    @Test
    fun notesScreen_shows_the_dialog_and_hides_onDelete_button_click() {
        with(composeTestRule) {
            onNodeWithContentDescription(floatingActionButtonDesc).assertIsDisplayed()
            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(dialogDescription).assertIsDisplayed()

            onNodeWithContentDescription(dialogDeleteButtonDescr).performClick()
            onNodeWithContentDescription(dialogDescription).assertIsNotDisplayed()

            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
        }
    }

    @Test
    fun noteDialog_saves_a_note() {
        val testNote = Note("Test title", "Test content")
        with(composeTestRule) {
            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(noteDialogTitleInputDescr).performTextInput(testNote.title)
            onNodeWithContentDescription(noteDialogTitleInputDescr).assertTextContains(testNote.title)
            onNodeWithContentDescription(noteDialogContentInputDescr).performTextInput(testNote.content)
            onNodeWithContentDescription(noteDialogContentInputDescr).assertTextContains(testNote.content)

            onNodeWithContentDescription(dialogSaveButtonDescr).performClick()
            onNodeWithContentDescription(dialogDescription).assertIsNotDisplayed()

            assertEquals(viewModel.currentNote.value, testNote)
        }
    }

    @Test
    fun notesDialog_appears_with_empty_note_onFAB_click() {
        with(composeTestRule) {
            val testNote = Note("Test title", "Test content")

            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(dialogDescription).assertIsDisplayed()

            onNodeWithContentDescription(noteDialogTitleInputDescr).performTextInput(testNote.title)
            onNodeWithContentDescription(noteDialogContentInputDescr).performTextInput(testNote.content)
            onNodeWithContentDescription(dialogSaveButtonDescr).performClick()

            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(noteDialogTitleInputDescr).assertTextContains("")
            onNodeWithContentDescription(noteDialogContentInputDescr).assertTextContains("")
        }
    }

    @Test
    fun on_wrong_input_errorMsg_is_shown() {
        val wrongTitle = "Hello World too long line for title"
        val singleLetter = "s"
        with(composeTestRule) {
            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()
            onNodeWithContentDescription(dialogSaveButtonDescr).assertIsNotEnabled()
            onNodeWithContentDescription(noteDialogTitleInputDescr).performTextInput(wrongTitle)
            onNodeWithContentDescription(errorMsgDescr).assertIsDisplayed()
            onNodeWithContentDescription(errorMsgDescr).assertTextEquals(errorTitleMsg)

            onNodeWithContentDescription(dialogDeleteButtonDescr).performClick()

            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()
            onNodeWithContentDescription(noteDialogContentInputDescr).performTextInput(singleLetter)
            onNodeWithContentDescription(errorMsgDescr).assertIsDisplayed()
            onNodeWithContentDescription(errorMsgDescr).assertTextEquals(errorContentMsg)

            onNodeWithContentDescription(dialogDeleteButtonDescr).performClick()
        }
    }

    @Test
    fun on_correct_input_errorMsg_is_not_shown() {
        val correctInput = "Correct input"
        with(composeTestRule) {
            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()
            onNodeWithContentDescription(dialogSaveButtonDescr).assertIsNotEnabled()
            onNodeWithContentDescription(noteDialogTitleInputDescr).performTextInput(correctInput)
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()

            onNodeWithContentDescription(floatingActionButtonDesc).performClick()
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()
            onNodeWithContentDescription(dialogSaveButtonDescr).assertIsNotEnabled()
            onNodeWithContentDescription(noteDialogContentInputDescr).performTextInput(correctInput)
            onNodeWithContentDescription(errorMsgDescr).assertIsNotDisplayed()
        }
    }
}