package com.kabira.boredapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kabira.boredapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        val btnNext: TextView = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
            finish()
        }
    }
}