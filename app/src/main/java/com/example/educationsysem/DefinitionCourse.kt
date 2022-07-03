package com.example.educationsysem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_definition_course.*
import modul.Course

class DefinitionCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition_course)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        var course = intent.getSerializableExtra("course") as Course

        titleee.text = course.courseName
        definition_text.text = course.coursText
        add_student_btn.setOnClickListener {
            var intent = Intent(this,AddStudent::class.java)
           intent.putExtra("name",titleee.text.toString())
            startActivity(intent)
        }
        defin_back.setOnClickListener {
            finish()
        }
    }
}