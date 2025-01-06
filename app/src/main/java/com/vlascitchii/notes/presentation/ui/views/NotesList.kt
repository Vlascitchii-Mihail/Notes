package com.vlascitchii.notes.presentation.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.utils.TagsNotesScreen

@Composable
fun NotesList(
    innerPadding: PaddingValues = PaddingValues(0.dp),
    notes: List<Note> = DEFAULT_NOTES,
    setDialogVisibilityState: (Boolean) -> Unit,
    setCurrentNote: (Note) -> Unit
    ) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.testTag(TagsNotesScreen.NOTES_LIST)
            .padding(innerPadding)
    ) {
        items(notes) {note ->
            NoteItem(note.title, note.content, setDialogVisibilityState, setCurrentNote)
        }
    }
}

private val DEFAULT_NOTES = listOf<Note>(
    Note(
        title = "test Note 1",
        content = "test Content description 1"
    ),
    Note(
        title = "test Note 2",
        content = "test Content description 2"
    ),
    Note(
        title = "test Note 3",
        content = "test Content description 3"
    ),
)

@Preview
@Composable
fun NotesListPreview() {
    val notes = listOf<Note>(
        Note(
            title = "test Note 1",
            content = "test Content description 1"
        ),
        Note(
            title = "test Note 2",
            content = "test Content description 2"
        ),
        Note(
            title = "test Note 3",
            content = "test Content description 3"
        ),
    )
    NotesList(notes = notes, setDialogVisibilityState = { isVisible: Boolean -> }, setCurrentNote = {note: Note ->})
}