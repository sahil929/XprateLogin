package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.myapplication.storage.Prefrences
import com.example.myapplication.utils.Constants

class BaseActivity : AppCompatActivity() {
    private lateinit var progressLayout: ConstraintLayout
    private lateinit var imageView: ImageView
    private lateinit var tvHowItWorks: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        progressLayout = findViewById<ConstraintLayout>(R.id.rlProgress)
        imageView = findViewById<ImageView>(R.id.ivCompany)
        tvHowItWorks = findViewById(R.id.tvHowItWorks)
        tvHowItWorks.setOnClickListener {
            startWebActivity(Constants.how_it_works_url)
        }
        Prefrences.storeToken(
            this,
            intent.getStringExtra(Prefrences.ACCESS_TOKEN) ?: "",
            intent.getStringExtra(Prefrences.SECRET_KEY) ?: ""
        )
    }

    fun showProgress() {
        loadImage()
        progressLayout.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressLayout.visibility = View.GONE
    }

    private fun loadImage() {
        Glide
            .with(this)
            .load(Prefrences.getImageUrl(this))
            .centerCrop()
            .placeholder(R.drawable.grey_bg)
            .into(imageView)
    }

     fun startWebActivity(url:String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(
            url)
        startActivity(openURL)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}