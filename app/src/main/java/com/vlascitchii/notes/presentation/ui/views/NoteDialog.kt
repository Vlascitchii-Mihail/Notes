package com.vlascitchii.notes.presentation.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vlascitchii.notes.R
import com.vlascitchii.notes.domain.input_validator.Validator
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.ui.theme.NotesTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NoteDialog(
    showNoteDialogVisibility: StateFlow<Boolean>,
    setDialogVisibilityState: (Boolean) -> Unit,
    currentNote: StateFlow<Note>,
    changeNote: (newNote: Note) -> Unit,
    saveNote: (Note) -> Unit,
    noteTitleValidator: (Note) -> Boolean,
    noteContentValidator: (Note) -> Boolean,
    modifier: Modifier = Modifier
) {
    val noteDialogVisibility by showNoteDialogVisibility.collectAsStateWithLifecycle()
    val note by currentNote.collectAsStateWithLifecycle()
    var isWrongNoteTitle by remember { mutableStateOf(false) }
    var isWrongNoteContent by remember { mutableStateOf(false) }
    Log.d("Dialog", "NoteDialog() $isWrongNoteTitle")

    val appContext = LocalContext.current

    val dialogDescription = remember { appContext.getString(R.string.dialog_description) }
    val addNoteTitle = remember { appContext.getString(R.string.add_note) }
    val changeNoteTitle = remember { appContext.getString(R.string.change_note) }
    val titleInputFieldLabel = remember { appContext.getString(R.string.note_dialog_title_input) }
    val contentInputFiledLabel =
        remember { appContext.getString(R.string.note_dialog_content_input) }
    val errorTitleInputMsg = remember { appContext.getString(R.string.error_msg_title) }
    val errorContentMsg = remember { appContext.getString(R.string.error_msg_content) }
    val dialogTitle = remember { if (note.title == "" && note.content == "") addNoteTitle else changeNoteTitle }

    val outlinedInputColor = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.onBackground,
        unfocusedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,

        cursorColor = MaterialTheme.colorScheme.onPrimary,

        focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,

        focusedLabelColor = MaterialTheme.colorScheme.onBackground,
        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,

        errorCursorColor = MaterialTheme.colorScheme.primary,
        errorBorderColor = MaterialTheme.colorScheme.primary,
        errorTextColor = MaterialTheme.colorScheme.primary
    )

    if (noteDialogVisibility) {
        AlertDialog(
            modifier = Modifier.semantics { contentDescription = dialogDescription }
                .fillMaxWidth(0.9F),
            properties = DialogProperties(usePlatformDefaultWidth = false),
            shape = MaterialTheme.shapes.extraSmall,
            onDismissRequest = {
                saveNote.invoke(note)
                setDialogVisibilityState.invoke(false)
            },
            title = { Text(dialogTitle) },
            text = {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .semantics { contentDescription = titleInputFieldLabel },
                        value = note.title,
                        onValueChange = {
                            val newNote = Note(it, note.content)
                            changeNote.invoke(newNote)
                            isWrongNoteTitle = noteTitleValidator.invoke(newNote)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = titleInputFieldLabel) },
                        isError = isWrongNoteTitle,
                        colors = outlinedInputColor,
                        shape = MaterialTheme.shapes.small
                    )
                    ErrorMessage(isWrongNoteTitle, errorTitleInputMsg)

                    OutlinedTextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .semantics { contentDescription = contentInputFiledLabel },
                        value = note.content,
                        onValueChange = {
                            val newNote = Note(note.title, it)
                            changeNote.invoke(newNote)
                            isWrongNoteContent = noteContentValidator.invoke(newNote)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = { Text(text = contentInputFiledLabel) },
                        isError = isWrongNoteContent,
                        colors = outlinedInputColor,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    ErrorMessage(isWrongNoteContent, errorContentMsg)
                }
            },
            confirmButton = {
                val confirmButtonText = stringResource(id = R.string.save)
                TextButton(
                    shape = MaterialTheme.shapes.small,
                    onClick = {
                        setDialogVisibilityState.invoke(false)
                        saveNote.invoke(note)
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier.semantics { contentDescription = confirmButtonText },
                    enabled = !noteTitleValidator.invoke(note) && !noteContentValidator.invoke(note)
                ) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                val deleteButtonText = stringResource(id = R.string.delete)
                TextButton(
                    shape = MaterialTheme.shapes.small,
                    onClick = {
                        setDialogVisibilityState.invoke(false)
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    modifier = Modifier.semantics { contentDescription = deleteButtonText }
                ) {
                    Text(text = deleteButtonText)
                }
            }
        )
    } else {
        isWrongNoteTitle = false
        isWrongNoteContent = false
    }
}


@Preview
@Composable
fun PreviewShowNote() {
    NotesTheme {
        val dialogVisibility =MutableStateFlow(true)
        val currentNote = MutableStateFlow(Note("Preview", "Note"))
        val validator = Validator()
        NoteDialog(
            showNoteDialogVisibility = dialogVisibility,
            setDialogVisibilityState = { isVisible -> dialogVisibility.value = isVisible},
            currentNote = currentNote,
            changeNote = { newNote: Note -> currentNote.value = newNote },
            saveNote = { newNote: Note -> currentNote.value = newNote },
            noteTitleValidator = { note: Note -> validator.validateTitleInput(note.title) },
            noteContentValidator = { note: Note -> validator.validateContentInput(note.content)},
        )
    }
}
