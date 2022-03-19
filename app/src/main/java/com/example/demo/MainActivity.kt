package com.example.demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.network.modals.XprateApp
import com.example.myapplication.network.show

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val xprate = XprateApp.Builder()
            .setSecretKey("ca89fcdc3add3dd0287bf391020181c2d2cb94c0cc571cf015bd0f3e29da83e0")
            .setSecretToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmdhbmlzYXRpb25JZCI6IjYxYmMyYmJjYzhkMDZmNmY1MjgyNjdhOSIsImRhdGUiOiIyMDIyLTAxLTI3VDIwOjEyOjMzLjQxOVoiLCJpYXQiOjE2NDMzMTQzNTN9.BfmHNkqsUZu9JR_w_gn9n_3_BWkxWJX1JzwpRAr9Bsw")
            .build()
        xprate.show(this, result)
    }

    var result =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                Log.e("Testing", "" + data?.getStringExtra("result"))
                Toast.makeText(this," "+data?.getStringExtra("result"), Toast.LENGTH_SHORT).show()
            }
        }
}