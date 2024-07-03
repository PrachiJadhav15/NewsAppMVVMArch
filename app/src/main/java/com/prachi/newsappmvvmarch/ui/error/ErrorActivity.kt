package com.prachi.newsappmvvmarch.ui.error

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prachi.newsappmvvmarch.databinding.ErrorActivityBinding

class ErrorActivity : AppCompatActivity() {

    private lateinit var binding: ErrorActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ErrorActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.tryAgainBtn.setOnClickListener {
            setResult(RESULT_OK, null);
            finish()
        }
    }
}
