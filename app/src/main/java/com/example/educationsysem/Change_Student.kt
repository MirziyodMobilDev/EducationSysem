package com.example.educationsysem

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_new_student.*
import kotlinx.android.synthetic.main.activity_change_student.*

class Change_Student : AppCompatActivity() {
    lateinit var datta:String
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_student)
        change_calendar.setOnClickListener {
            var datePicker = DatePickerDialog(this)
            datePicker.setOnDateSetListener{datePicker,i1,i2,i3 ->
                datta = "$i1/$i2/$i3"
                change_date.setText(datta)
            }
            datePicker.show()
        }
        courseDatabaseHelper = CourseDatabaseHelper(this)
        var id = intent.getIntExtra("ID",0)
        var changeStudent = courseDatabaseHelper.getnewStudentById(id)
        change_lastName.setText(changeStudent.new_add_familya)
        change_name.setText(changeStudent.new_add_ism)
        change_fathers.setText(changeStudent.new_add_otasi)
        change_date.setText(changeStudent.new_add_date)
        student_change_btn.setOnClickListener{
            var lastName = change_lastName.text.toString()
            var name = change_name.text.toString()
            var father = change_fathers.text.toString()
            if (lastName.isNotBlank()&&name.isNotBlank()&&father.isNotBlank()&&datta.isNotBlank()) {

            }
            else{
                Toast.makeText(this,"malumotlar to'liq kiritimadi", Toast.LENGTH_LONG).show()

            }
            finish()

        }
        change_back.setOnClickListener {
            finish()
        }
    }
}