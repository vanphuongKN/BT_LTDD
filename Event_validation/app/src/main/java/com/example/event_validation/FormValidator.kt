package com.example.event_validation


import android.util.Patterns
import androidx.annotation.StringRes

data class ValidationResult(
    val isValid: Boolean,
    @StringRes val errorRes: Int? = null
)

object FormValidator {

    private val NAME_REGEX = "^[\\p{L}\\p{M} ]+$".toRegex()          // chữ (kể cả có dấu) + khoảng trắng
    private val PHONE_REGEX = "^(03|05|07|08|09)\\d{8}$".toRegex()  // SĐT di động VN 10 số
    private val STUDENT_ID_REGEX = "^\\d{10}$".toRegex()           // MSV đúng 10 chữ số
    private val CLASS_REGEX = "^[A-Za-z0-9]{3,15}$".toRegex()      // vd: 22T1, 23CNTT2

    fun validateName(name: String) = when {
        name.isBlank() -> ValidationResult(false, R.string.err_name_empty)
        !name.matches(NAME_REGEX) -> ValidationResult(false, R.string.err_name_invalid)
        name.split("\\s+".toRegex()).size < 2 -> ValidationResult(false, R.string.err_name_short)
        else -> ValidationResult(true)
    }

    fun validateEmail(email: String) = when {
        email.isBlank() -> ValidationResult(false, R.string.err_email_empty)
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> ValidationResult(false, R.string.err_email_invalid)
        else -> ValidationResult(true)
    }

    fun validatePhone(phone: String) = when {
        phone.isBlank() -> ValidationResult(false, R.string.err_phone_empty)
        !phone.matches(PHONE_REGEX) -> ValidationResult(false, R.string.err_phone_invalid)
        else -> ValidationResult(true)
    }

    fun validateStudentId(id: String) = when {
        id.isBlank() -> ValidationResult(false, R.string.err_student_id_empty)
        !id.matches(STUDENT_ID_REGEX) -> ValidationResult(false, R.string.err_student_id_invalid)
        else -> ValidationResult(true)
    }

    fun validateClass(className: String) = when {
        className.isBlank() -> ValidationResult(false, R.string.err_class_empty)
        !className.matches(CLASS_REGEX) -> ValidationResult(false, R.string.err_class_invalid)
        else -> ValidationResult(true)
    }
}