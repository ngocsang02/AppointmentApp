package com.tutorials.appointmentapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tutorials.appointmentapp.R
import com.tutorials.appointmentapp.domain.DoctorsModel

class DetailActivity : AppCompatActivity() {

    private lateinit var backDetailBtn:ImageView
    private lateinit var favDetailBtn:ImageView
    private lateinit var imgDetailDoctor:ImageView

    private lateinit var titleDetailTxt:TextView
    private lateinit var specialDetailTxt:TextView
    private lateinit var addressDetailTxt:TextView
    private lateinit var patiensDetailTxt:TextView
    private lateinit var experiencesDetailTxt:TextView
    private lateinit var ratingDetailTxt:TextView
    private lateinit var bioDetailTxt:TextView


    private lateinit var websiteDetailBtn:LinearLayout
    private lateinit var messageDetailBtn:LinearLayout
    private lateinit var callDetailBtn:LinearLayout
    private lateinit var directionDetailBtn:LinearLayout
    private lateinit var shareDetailBtn:LinearLayout

    private lateinit var makeAppointmentBtn:Button


    private lateinit var item:DoctorsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initFindViewById()

        getBundleForDetail()
    }

    private fun initFindViewById() {
        backDetailBtn = findViewById(R.id.backBtn)
        favDetailBtn = findViewById(R.id.favBtn)
        imgDetailDoctor = findViewById(R.id.imgDetailDoctor)

        titleDetailTxt = findViewById(R.id.titleDetailTxt)
        specialDetailTxt = findViewById(R.id.specialDetailTxt)
        addressDetailTxt = findViewById(R.id.addressDetailTxt)
        patiensDetailTxt = findViewById(R.id.patiensDetailTxt)
        experiencesDetailTxt = findViewById(R.id.experienceDetailTxt)
        ratingDetailTxt = findViewById(R.id.ratingDetailTxt)
        bioDetailTxt = findViewById(R.id.bioDetailTxt)

        websiteDetailBtn = findViewById(R.id.websiteDetailBtn)
        messageDetailBtn = findViewById(R.id.messageDetailBtn)
        callDetailBtn = findViewById(R.id.callDetailBtn)
        directionDetailBtn = findViewById(R.id.directionDetailBtn)
        shareDetailBtn = findViewById(R.id.shareDetailBtn)

        makeAppointmentBtn = findViewById(R.id.makeDetailBtn)
    }

    private fun getBundleForDetail() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            item = intent.getParcelableExtra("object", DoctorsModel::class.java)!!
        }else{
            item = intent.getParcelableExtra("object")!!
        }

        titleDetailTxt.text = item.Name
        specialDetailTxt.text = item.Special
        addressDetailTxt.text = item.Address
        patiensDetailTxt.text = item.Patiens
        experiencesDetailTxt.text = item.Expriense.toString()
        ratingDetailTxt.text = item.Rating.toString()
        bioDetailTxt.text = item.Biography

        initSetOnClick()

        Glide.with(this@DetailActivity)
            .load(item.Picture)
            .into(imgDetailDoctor)
    }

    private fun initSetOnClick() {
        backDetailBtn.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })

        websiteDetailBtn.setOnClickListener {
            val intentOpenWeb = Intent(Intent.ACTION_VIEW)
            intentOpenWeb.setData(Uri.parse(item.Site))
            startActivity(intentOpenWeb)
        }

        messageDetailBtn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val uri = Uri.parse("smsto: ${item.Mobile}")
                val intentMessage = Intent(Intent.ACTION_SENDTO, uri)
                intentMessage.putExtra("sms_body", "the SMS text")
                startActivity(intentMessage)
            }
        })

        callDetailBtn.setOnClickListener {
            val uri = "tel:" + item.Mobile.trim()
            val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse(uri))
            startActivity(intentCall)
        }


        directionDetailBtn.setOnClickListener {
            val intentDirection = Intent(Intent.ACTION_VIEW, Uri.parse(item.Location))
            startActivity(intentDirection)
        }

        shareDetailBtn.setOnClickListener {
            val intentShare = Intent(Intent.ACTION_SEND)
            intentShare.setType("text/plain")
            intentShare.putExtra(Intent.EXTRA_SUBJECT, item.Name)
            intentShare.putExtra(Intent.EXTRA_TEXT, item.Name+" "+item.Address+" "+item.Mobile)
            startActivity(Intent.createChooser(intentShare, "Choose one"))
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            onBackPressedDispatcher.addCallback(this, object :OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    // Xử lý hành động back ở đây
                    finish()  // Nếu muốn vẫn giữ lại hành vi cũ
                }
            })
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}