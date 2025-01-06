package com.vlascitchii.notes.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenType {

    @Serializable
    data object NotesList: ScreenType()
}