package com.example.educationsysem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kurs_btn.setOnClickListener {
            var intent = Intent(this, AdroidKoursAdd::class.java)
            startActivity(intent)
        }

        mentor_btn.setOnClickListener {
            var intent = Intent(this,Mentors::class.java)
            startActivity(intent)
        }
        guruh_btn.setOnClickListener {
            var intent = Intent(this,Guruhlar::class.java)
            startActivity(intent)
        }
    }
}