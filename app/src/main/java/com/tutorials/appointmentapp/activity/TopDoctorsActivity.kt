package com.tutorials.appointmentapp.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.appointmentapp.R
import com.tutorials.appointmentapp.adapter.TopDoctorAdapter2
import com.tutorials.appointmentapp.viewModel.MainViewModel

class TopDoctorsActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    //TopDoctor
    private lateinit var progressBarTopDoctors: ProgressBar
    private lateinit var viewTopDoctor: RecyclerView

    private lateinit var backTopDoctorsBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_doctors)

        initFindViewById()
        initTopDoctor()
    }


    private fun initFindViewById() {
        //TopDoctor
        progressBarTopDoctors = findViewById(R.id.progressBarTopDoctors)
        viewTopDoctor = findViewById(R.id.recyclerViewTopDoctors)

        backTopDoctorsBtn = findViewById(R.id.backTopDoctors)
    }

    private fun initTopDoctor() {
        progressBarTopDoctors.visibility = View.VISIBLE
        viewModel.doctors.observe(this, Observer {
            viewTopDoctor.layoutManager = LinearLayoutManager(
                this@TopDoctorsActivity,
                LinearLayoutManager.VERTICAL,
                false)
            viewTopDoctor.adapter = TopDoctorAdapter2(it)

            progressBarTopDoctors.visibility = View.GONE
        })

        viewModel.loadDoctors()

        backTopDoctorsBtn.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }

        })

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    // Xử lý hành động back ở đây
                    finish()  // Nếu muốn vẫn giữ lại hành vi cũ
                }
            })
        }
    }
}