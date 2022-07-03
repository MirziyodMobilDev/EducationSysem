package com.example.educationsysem

import Adapters.MyAddAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_adroid_kours_add.*
import kotlinx.android.synthetic.main.activity_mentors.*
import modul.Course

class Mentors : AppCompatActivity() {

    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var myAddAdapter: MyAddAdapter
    lateinit var courseList:ArrayList<Course>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors)

        courseList = ArrayList()
        courseDatabaseHelper = CourseDatabaseHelper(this)
        courseList = courseDatabaseHelper.getAllCourse()


        myAddAdapter = MyAddAdapter(courseList,object : MyAddAdapter.OnClickListener{
            override fun onClick(course: Course, position: Int) {
                var intent = Intent(this@Mentors,MentorsAdd::class.java)
                val bundle = Bundle()
                bundle.putSerializable("course",course)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })

        mentor_rv.adapter = myAddAdapter
        myAddAdapter.notifyItemInserted(courseList.size)
        myAddAdapter.notifyDataSetChanged()

        mentors_back.setOnClickListener {
            finish()
        }
    }
}