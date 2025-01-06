package com.vlascitchii.notes.presentation.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.vlascitchii.notes.R

@Composable
fun ErrorMessage(isValidInput: Boolean, errorMessage: String) {
    val errorMessageDescription = stringResource(R.string.error_msg_description)
    if (isValidInput) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .semantics { contentDescription = errorMessageDescription }
        )
    }
}