package com.tutorials.appointmentapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.appointmentapp.adapter.CategoryAdapter
import com.tutorials.appointmentapp.adapter.TopDoctorAdapter
import com.tutorials.appointmentapp.R
import com.tutorials.appointmentapp.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    //Category
    private lateinit var progressBarCategory:ProgressBar
    private lateinit var viewCategory:RecyclerView

    //TopDoctor
    private lateinit var progressBarTopDoctor:ProgressBar
    private lateinit var viewTopDoctor:RecyclerView

    private lateinit var seeAllTopDoctors:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFindViewById()
        initCategory()

        initTopDoctor()

        initSetOnClick()
    }

    private fun initFindViewById() {
        //Category
        progressBarCategory = findViewById(R.id.progressBarCategory)
        viewCategory = findViewById(R.id.viewCategory)

        //TopDoctor
        progressBarTopDoctor = findViewById(R.id.progressBarTopDoctor)
        viewTopDoctor = findViewById(R.id.recyclerViewTopDoctor)

        seeAllTopDoctors = findViewById(R.id.seeAllTopDoctors)
    }

    private fun initCategory() {
        progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            viewCategory.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false)

            viewCategory.adapter = CategoryAdapter(it)

            progressBarCategory.visibility = View.GONE
        })

        viewModel.loadCategory()
    }

    private fun initTopDoctor() {
        progressBarTopDoctor.visibility = View.VISIBLE
        viewModel.doctors.observe(this, Observer {
            viewTopDoctor.layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false)
            viewTopDoctor.adapter = TopDoctorAdapter(it)

            progressBarTopDoctor.visibility = View.GONE
        })

        viewModel.loadDoctors()
    }

    private fun initSetOnClick() {
        seeAllTopDoctors.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val intent = Intent(this@MainActivity, TopDoctorsActivity::class.java)
                startActivity(intent)
            }
        })
    }

}