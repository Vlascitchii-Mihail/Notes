package com.vlascitchii.notes.presentation.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vlascitchii.notes.R
import com.vlascitchii.notes.presentation.ui.theme.NotesTheme
import com.vlascitchii.notes.utils.TagsNotesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesTopAppBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.top_app_bar_name)) },
        modifier = Modifier.testTag(TagsNotesScreen.TOP_APP_BAR),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = Color.White,
        )
    )
}

@Preview
@Composable
fun NotesTopAppBarPreview() {
    NotesTheme {
        NotesTopAppBar()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NotesTopAppBarDarkPreview() {
    NotesTheme {
        NotesTopAppBar()
    }
}

