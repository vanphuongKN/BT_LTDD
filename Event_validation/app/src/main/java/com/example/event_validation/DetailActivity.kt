package com.example.event_validation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.event_validation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        // Nhận dữ liệu từ Intent và hiển thị
        binding.tvName.text = getString(R.string.label_name, intent.getStringExtra(EXTRA_NAME).orEmpty())
        binding.tvEmail.text = getString(R.string.label_email, intent.getStringExtra(EXTRA_EMAIL).orEmpty())
        binding.tvPhone.text = getString(R.string.label_phone, intent.getStringExtra(EXTRA_PHONE).orEmpty())
        binding.tvStudentId.text = getString(R.string.label_student_id, intent.getStringExtra(EXTRA_STUDENT_ID).orEmpty())
        binding.tvClass.text = getString(R.string.label_class, intent.getStringExtra(EXTRA_CLASS).orEmpty())

        // Nút gọi Intent quay về màn Đăng kí (giữ nguyên dữ liệu form cũ)
        binding.btnGoRegister.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(backIntent)
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_PHONE = "extra_phone"
        const val EXTRA_STUDENT_ID = "extra_student_id"
        const val EXTRA_CLASS = "extra_class"

        fun newIntent(context: Context, student: Student): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, student.name)
                putExtra(EXTRA_EMAIL, student.email)
                putExtra(EXTRA_PHONE, student.phone)
                putExtra(EXTRA_STUDENT_ID, student.studentId)
                putExtra(EXTRA_CLASS, student.className)
            }
    }
}