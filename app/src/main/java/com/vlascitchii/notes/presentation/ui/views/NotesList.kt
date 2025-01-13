package com.vlascitchii.notes.presentation.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
    setCurrentNote: (Note) -> Unit,
    windowWidth: WindowWidthSizeClass
    ) {

    when(windowWidth) {
        WindowWidthSizeClass.Compact -> {
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
        else -> {
            LazyVerticalGrid(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.testTag(TagsNotesScreen.NOTES_LIST)
                    .padding(innerPadding),
                columns = GridCells.Fixed(2),
            ) {
                items(notes) {note ->
                    NoteItem(note.title, note.content, setDialogVisibilityState, setCurrentNote)
                }
            }
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
    Note(
        title = "test Note 3",
        content = "test Content description 3"
    ),
    Note(
        title = "test Note 3",
        content = "test Content description 3"
    ),
    Note(
        title = "test Note 3",
        content = "test Content description 3"
    ),
    Note(
        title = "test Note 3",
        content = "test Content description 3"
    ),
)

@Preview
@Composable
fun NotesListPreview() {
    NotesList(
        notes = DEFAULT_NOTES,
        setDialogVisibilityState = { isVisible: Boolean -> },
        setCurrentNote = {note: Note ->},
        windowWidth = WindowWidthSizeClass.Compact
    )
}

@Preview(uiMode = Configuration.ORIENTATION_LANDSCAPE)
@Composable
fun NotesListLandscapePreview() {
    NotesList(
        notes = DEFAULT_NOTES,
        setDialogVisibilityState = { isVisible: Boolean -> },
        setCurrentNote = {note: Note ->},
        windowWidth = WindowWidthSizeClass.Expanded
    )
}