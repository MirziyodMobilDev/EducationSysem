package com.example.educationsysem

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_new_student.*
import modul.NewStudentAdd

class Add_New_Student : AppCompatActivity() {
    lateinit var datta:String
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_student)
        courseDatabaseHelper = CourseDatabaseHelper(this)
        new_calendar.setOnClickListener {
                var datePicker = DatePickerDialog(this)
                datePicker.setOnDateSetListener{datePicker,i1,i2,i3 ->
                    datta = "$i1/$i2/$i3"
                    new_add_s_date.setText(datta)
                }
                datePicker.show()

        }
        new_student_saqlash_btn.setOnClickListener {
            var gName = intent.getStringExtra("gruhname")
          var familya = new_add_s_lName.text.toString()
            var ism = new_add_s_name.text.toString()
            var ota = new_add_s_father.text.toString()
            if (ism.isNotBlank()&&familya.isNotBlank()&&ota.isNotBlank()&&datta.isNotBlank()) {
                var newStudentAdd = NewStudentAdd(familya,ism,ota,datta,gName.toString())
                courseDatabaseHelper.InsertNewStudent(newStudentAdd)
            }else{
                Toast.makeText(this,"${gName}",Toast.LENGTH_SHORT).show()
            }
            finish()
        }
        add_S_back.setOnClickListener {
            finish()
        }

    }
}