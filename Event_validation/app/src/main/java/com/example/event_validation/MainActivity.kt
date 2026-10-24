package com.example.event_validation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.event_validation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lưu sinh viên đã đăng kí thành công (null = chưa đăng kí hợp lệ)
    private var registeredStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        setupRealtimeClearError()

        binding.btnRegister.setOnClickListener { handleRegister() }

        binding.btnGoDetail.setOnClickListener {
            val student = registeredStudent
            if (student == null) {
                Snackbar.make(binding.root, R.string.msg_need_register_first, Snackbar.LENGTH_SHORT).show()
            } else {
                openDetail(student)
            }
        }
    }

    /** Xóa lỗi ngay khi người dùng gõ lại vào ô bị lỗi */
    private fun setupRealtimeClearError() {
        val fields = listOf(
            binding.tilName to binding.etName,
            binding.tilEmail to binding.etEmail,
            binding.tilPhone to binding.etPhone,
            binding.tilStudentId to binding.etStudentId,
            binding.tilClass to binding.etClass
        )
        for ((til, et) in fields) {
            et.doAfterTextChanged {
                til.error = null
                registeredStudent = null   // dữ liệu đã đổi → phải đăng kí lại
            }
        }
    }

    /** Kiểm tra toàn bộ form (báo lỗi đồng loạt) */
    private fun handleRegister() {
        val name = binding.etName.value()
        val email = binding.etEmail.value()
        val phone = binding.etPhone.value()
        val studentId = binding.etStudentId.value()
        val className = binding.etClass.value().uppercase()

        val checks = listOf(
            Triple(binding.tilName, binding.etName, FormValidator.validateName(name)),
            Triple(binding.tilEmail, binding.etEmail, FormValidator.validateEmail(email)),
            Triple(binding.tilPhone, binding.etPhone, FormValidator.validatePhone(phone)),
            Triple(binding.tilStudentId, binding.etStudentId, FormValidator.validateStudentId(studentId)),
            Triple(binding.tilClass, binding.etClass, FormValidator.validateClass(className))
        )

        var firstErrorField: View? = null
        for ((til, et, result) in checks) {
            if (result.isValid) {
                til.error = null
            } else {
                til.error = getString(result.errorRes!!)
                if (firstErrorField == null) firstErrorField = et
            }
        }

        // FAIL → hiển thị lỗi
        if (firstErrorField != null) {
            registeredStudent = null
            firstErrorField.requestFocus()
            Snackbar.make(binding.root, R.string.msg_register_failed, Snackbar.LENGTH_SHORT).show()
            return
        }

        // PASS → Snackbar thành công + nút sang màn Chi tiết
        val student = Student(name, email, phone, studentId, className)
        registeredStudent = student
        hideKeyboard()
        Snackbar.make(binding.root, getString(R.string.msg_register_success, name), Snackbar.LENGTH_LONG)
            .setAction(R.string.action_detail) { openDetail(student) }
            .show()
    }

    private fun openDetail(student: Student) {
        startActivity(DetailActivity.newIntent(this, student))
    }

    private fun hideKeyboard() {
        val imm = getSystemService(InputMethodManager::class.java)
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun TextInputEditText.value(): String = text?.toString()?.trim().orEmpty()
}