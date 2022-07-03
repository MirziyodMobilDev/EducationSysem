package com.example.educationsysem

import Adapters.DaySpinner
import Adapters.MentorSpiner
import Adapters.TimeSpiner
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_new_group.*
import kotlinx.android.synthetic.main.activity_add_student.*
import modul.DayForSpiner
import modul.GruhList
import modul.Mentor
import modul.TimeForSpiner

class AddNewGroup : AppCompatActivity() {
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var mList:ArrayList<Mentor>
    lateinit var mListt:ArrayList<Mentor>
    lateinit var timeList:ArrayList<TimeForSpiner>
    lateinit var mentorSpiner: MentorSpiner
    lateinit var timeSpiner: TimeSpiner
    lateinit var dayList:ArrayList<DayForSpiner>
    lateinit var daySpinner: DaySpinner
    lateinit var intentNamee:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_group)
        mList = ArrayList()
        mListt = ArrayList()
        courseDatabaseHelper = CourseDatabaseHelper(this)
        mList = courseDatabaseHelper.getAllMentor()
        var course_name = intent.getStringExtra("coursename")
        intentNamee = course_name.toString()
        for(i in 0 until mList.size){
            if(mList[i].courseName == intentNamee){
                mListt.add(mList[i])
            }
        }
        mentorSpiner = MentorSpiner(mListt)
        add_new_mentor.adapter = mentorSpiner

        time()
        timeSpiner = TimeSpiner(timeList)
        add_new_time_group.adapter = timeSpiner
        day()
        daySpinner = DaySpinner(dayList)
        add_new_day_group.adapter = daySpinner

        new_group_btn.setOnClickListener {
            var course_name = intent.getStringExtra("coursename")
            var groupName = add_new_group_name.text.toString().trim()
            var  mentor_name =mList[add_new_mentor.selectedItemPosition].mentorName
            var times = timeList[add_new_time_group.selectedItemPosition].timeName
            var days = dayList[add_new_day_group.selectedItemPosition].dayName
            var gruhList = GruhList(groupName,mentor_name,times,days,"true",course_name)
                courseDatabaseHelper.Insertguruh(gruhList)
            Toast.makeText(this,gruhList.day, Toast.LENGTH_SHORT).show()


            finish()



        }
        add_new_back.setOnClickListener {
            finish()
        }
    }

    fun time(){
        timeList = ArrayList()
        timeList.add(TimeForSpiner("8.30 : 10.30"))
        timeList.add(TimeForSpiner("10.30 : 13.30"))
        timeList.add(TimeForSpiner("13.30 : 15.30"))
        timeList.add(TimeForSpiner("15.30 : 17.30"))
        timeList.add(TimeForSpiner("17.30 : 19.30"))
    }
    fun day(){
        dayList = ArrayList()
        dayList.add(DayForSpiner("Dush/Chor/Juma"))
        dayList.add(DayForSpiner("Sesh/Pay/Shanba"))
    }

}