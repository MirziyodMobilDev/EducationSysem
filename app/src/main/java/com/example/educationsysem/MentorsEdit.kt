package com.example.educationsysem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_mentors_edit.*
import modul.Course
import modul.Mentor

class MentorsEdit : AppCompatActivity() {
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var mentorList:ArrayList<Mentor>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors_edit)
     courseDatabaseHelper = CourseDatabaseHelper(this)
        mentor_qoshish_btn.setOnClickListener {
            var intentt = intent.getStringExtra("nameee")
            var lastname = familiya.text.toString()
            var name = ismi.text.toString()
            var father = otasining_ismi.text.toString()
            var mentors = Mentor(lastname,name,father,intentt)
            courseDatabaseHelper.InsertMentor(mentors)
            finish()

        }
        m_edit_back.setOnClickListener {
            finish()
        }


    }
}