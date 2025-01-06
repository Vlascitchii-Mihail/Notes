package com.vlascitchii.notes.presentation.ui.views

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.vlascitchii.notes.R
import com.vlascitchii.notes.domain.model.Note
import com.vlascitchii.notes.presentation.ui.theme.NotesTheme

@Composable
fun NotesFloatingActionButton(
    setDialogVisibilityState: (Boolean) -> Unit,
    setCurrentNote: (Note) -> Unit,
) {
    val appContext = LocalContext.current
    val description = remember { appContext.getString(R.string.add_new_note_description) }
    FloatingActionButton(
        onClick = {
            setCurrentNote.invoke(Note())
            setDialogVisibilityState.invoke(true)
        },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        content = {
            Icon(
                painterResource(id = R.drawable.note_add_24),
                contentDescription = description
            )
        },
        modifier = Modifier.semantics { contentDescription = description }
    )
}

@Preview
@Composable
fun PreviewNotesFloatingActionButton() {
    NotesTheme {
        NotesFloatingActionButton({}, {})
    }
}