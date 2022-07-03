package com.example.educationsysem

import Adapters.ViewPegerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_open_and_opened_group.*
import modul.Course

class OpenAndOpenedGroup : AppCompatActivity() {
    lateinit var titleList: ArrayList<String>
    lateinit var viewPegerAdapter: ViewPegerAdapter
    lateinit var intentString:String
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_and_opened_group)
        courseDatabaseHelper = CourseDatabaseHelper(this)
        var id = intent.getIntExtra("id",0)
        var course = courseDatabaseHelper.getByIdCourse(id)
        title_name.text = course.courseName
        intentString = title_name.text.toString()
             add_open_newGroup.setOnClickListener {
            var intent = Intent(this, AddNewGroup::class.java)
            intent.putExtra("coursename", title_name.text.toString())
            startActivity(intent)
        }

        titleList = ArrayList()
        titleList.add("ochilgan guruhlar")
        titleList.add("ochilayotgan guruhlar")

        viewPegerAdapter = ViewPegerAdapter(titleList, supportFragmentManager,course.courseName!!)
        tab_layout.setupWithViewPager(viewPager)

        tab_layout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var pos = tab?.position
                when (pos) {
                    0 -> {
                     viewPager.currentItem = 0
                        add_open_newGroup.visibility = View.INVISIBLE

                    }
                    1 -> {
                        viewPager.currentItem = 1
                        add_open_newGroup.visibility = View.VISIBLE

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        open_back.setOnClickListener {
            finish()
        }




    }

    override fun onResume() {
        super.onResume()
        viewPager.adapter = viewPegerAdapter
    }
}