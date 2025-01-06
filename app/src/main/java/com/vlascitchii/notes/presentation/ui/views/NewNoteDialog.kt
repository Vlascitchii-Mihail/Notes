package com.vlascitchii.notes.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlascitchii.notes.R
import com.vlascitchii.notes.presentation.ui.theme.NotesTheme

//@Composable
//fun NewNoteDialog(newNoteDialogVisibility: MutableState<Boolean>, modifier: Modifier = Modifier) {
//    AlertDialog(
//        modifier = modifier,
//        shape = MaterialTheme.shapes.extraSmall,
//        onDismissRequest = {
//            /*TODO: add action*/
//            newNoteDialogVisibility.value = false
//        },
//        dismissButton = {
//            TextButton(
//                shape = MaterialTheme.shapes.medium,
//                onClick = {
//                    /*TODO: add action*/
//                    newNoteDialogVisibility.value = false
//                },
//                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
//            ) {
//                Text(text = stringResource(id = R.string.delete))
//            }
//        },
//        confirmButton = {
//            TextButton(
//                shape = MaterialTheme.shapes.medium,
//                onClick = {
//                    /*TODO: add action*/
//                    newNoteDialogVisibility.value = false
//                },
//                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
//            ) {
//                Text(text = stringResource(id = R.string.save))
//            }
//        },
//        title = {
//            Text(text = stringResource(id = R.string.add_note))
//        },
//        text = {
//            Column(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .verticalScroll(rememberScrollState()),
//                verticalArrangement = Arrangement.Center
//            ) {
//                OutlinedTextField(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    value = "",
//                    onValueChange = {},
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                    label = { Text(text = stringResource(id = R.string.note_title_oif_label)) }
//                )
//
//                OutlinedTextField(
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    value = "",
//                    onValueChange = {},
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                    label = { Text(text = stringResource(id = R.string.note_content_oif_label))}
//                )
//            }
//        }
//    )
//}
//
//@Preview
//@Composable
//private fun NewNotePreview() {
//    val isVisible = remember { mutableStateOf(true) }
//    NotesTheme {
//        NewNoteDialog(isVisible)
//    }
//}