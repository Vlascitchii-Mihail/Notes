package com.vlascitchii.notes.domain.input_validator

import java.util.regex.Pattern

private const val CONTENT_REGEX = "^[A-Za-z0-9`~!@#$%^&*() _+=\"',:;?\\\\/]{2,}$"
private const val TITLE_REGEX = "^[A-Za-z0-9`~!@#$%^&*() _+=\"':,;?\\\\/]{2,25}$"

class Validator {

    fun validateTitleInput(titleInput: String) = validate(titleInput, TITLE_REGEX)

    fun validateContentInput(contentInput: String) = validate(contentInput, CONTENT_REGEX)

    private fun validate(input: String, regex: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(input)
        return !matcher.matches()
    }
}
