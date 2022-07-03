package com.example.educationsysem

import Adapters.MyAddAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_guruhlar.*
import modul.Course

class Guruhlar : AppCompatActivity() {
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var myAddAdapter: MyAddAdapter
    lateinit var newGroupAddFragment: NewGroupAddFragment
    lateinit var list:ArrayList<Course>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guruhlar)
        courseDatabaseHelper = CourseDatabaseHelper(this)
        list = courseDatabaseHelper.getAllCourse()
        myAddAdapter = MyAddAdapter(list,object :MyAddAdapter.OnClickListener{
            override fun onClick(course: Course, position: Int) {
                var intent = Intent(this@Guruhlar,OpenAndOpenedGroup::class.java)
              intent.putExtra("id",course.coursId)
                startActivity(intent)
            }

        })
        group_rv.adapter =myAddAdapter
        guruh_back.setOnClickListener {
            finish()
        }
    }
}