package com.example.educationsysem

import Adapters.MyAddAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_adroid_kours_add.*
import kotlinx.android.synthetic.main.item_dialog.*
import modul.Course

class AdroidKoursAdd : AppCompatActivity() {
    lateinit var courseDatabaseHelper:CourseDatabaseHelper
    lateinit var myAddAdapter: MyAddAdapter
    lateinit var courseList:ArrayList<Course>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseDatabaseHelper = CourseDatabaseHelper(this)
        courseList = courseDatabaseHelper.getAllCourse()
        setContentView(R.layout.activity_adroid_kours_add)
        add.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
            dialog.setView(R.layout.item_dialog)
            var aDialog = dialog.show()
            aDialog.qoshish.setOnClickListener {
                var name = aDialog.add_course_name.text.toString().trim()
                var text = aDialog.add_course_text.text.toString().trim()
                if (name.isNotBlank()&&text.isNotBlank()) {
                    var course = Course(name, text)
                    courseList.add(course)
                    courseDatabaseHelper.InsertCourse(course)
                }
                else{
                    Toast.makeText(this,"malumotlar to'liq kiritimadi", Toast.LENGTH_LONG).show()

                }
                aDialog.dismiss()


            }
        }
        myAddAdapter = MyAddAdapter(courseList,object :MyAddAdapter.OnClickListener{
            override fun onClick(course: Course, position: Int) {
                var intent = Intent(this@AdroidKoursAdd,DefinitionCourse::class.java)
                val bundle = Bundle()
                bundle.putSerializable("course",course)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        rv.adapter = myAddAdapter
        myAddAdapter.notifyDataSetChanged()
        myAddAdapter.notifyItemInserted(courseList.size)
        orqa.setOnClickListener {
            finish()
        }
    }
}