package com.vlascitchii.notes.presentation.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.ui.theme.NotesTheme

@Composable
fun NoteItem(
    title: String,
    content: String,
    setDialogVisibilityState: (Boolean) -> Unit,
    setCurrentNote: (Note) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                setCurrentNote.invoke(Note(title, content))
                setDialogVisibilityState.invoke(true)
            }
            .padding(bottom = 1.dp)

    ) {
        Card(
            shape = MaterialTheme.shapes.extraSmall,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp, )
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Text(
                text = content,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 4.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable fun NoteItemLightPreview() {
    NotesTheme {
        NoteItem(
            "Test title",
            "Test content",
            setDialogVisibilityState = { isDialogVisible: Boolean -> },
            setCurrentNote = { note: Note ->}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable fun NoteItemDarkPreview() {
    NotesTheme {
        NoteItem(
            "Test title",
            "Test content",
            setDialogVisibilityState = { isDialogVisible: Boolean -> },
            setCurrentNote = { note: Note ->}
        )
    }
}
