package com.tutorials.appointmentapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.tutorials.appointmentapp.R

class IntroActivity : BaseActivity() {

    private lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        initFindViewById()

        btn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }

    private fun initFindViewById() {
        btn = findViewById(R.id.startBtn)

    }
}