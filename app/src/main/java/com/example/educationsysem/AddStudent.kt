package com.example.educationsysem

import Adapters.GroupSpinner
import Adapters.MentorSpiner
import Kusrsconstant.Constant
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_student.*
import modul.*

class AddStudent : AppCompatActivity() {
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var mentorSpiner: MentorSpiner
    lateinit var groupSpiner: GroupSpinner
    lateinit var mList:ArrayList<Mentor>
    lateinit var mListt:ArrayList<Mentor>
    lateinit var gList:ArrayList<GruhList>
    lateinit var gList1:ArrayList<GruhList>
    lateinit var datta:String
    lateinit var intentName:String
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        courseDatabaseHelper = CourseDatabaseHelper(this)

        calendar.setOnClickListener {
            var datePicker = DatePickerDialog(this)
            datePicker.setOnDateSetListener{datePicker,i1,i2,i3 ->
                datta = "$i1/$i2/$i3"
                edit_calendar.setText(datta)

            }
            datePicker.show()
        }

        var cours = intent.getStringExtra("name")
        mListt = ArrayList()
        mList = ArrayList()
        mList = courseDatabaseHelper.getAllMentor()
        for(i in 0 until mList.size){
            if(mList[i].courseName == cours){
                mListt.add(mList[i])
            }
            else{
                Toast.makeText(this,cours,Toast.LENGTH_LONG).show()}
        }
        mentorSpiner = MentorSpiner(mListt)
        mentor_spinner.adapter = mentorSpiner

        gList1 = ArrayList()
        gList= ArrayList()
        gList = courseDatabaseHelper.getAllGuruh()
        for (i in 0 until gList.size){
            if (gList[i].course == cours){
                gList1.add(gList[i])
            }
        }
        groupSpiner = GroupSpinner(gList1)
        group_spinner.adapter = groupSpiner




        Student_saqlash_btn.setOnClickListener {
            var surnamee =  surname.text.toString()
            var nameee = name.text.toString()
            var middle_namee =  middle_name.text.toString()
            var mentor =mentor_spinner.selectedItem.toString()
            var group = gList[group_spinner.selectedItemPosition].gruhName
            if (surnamee.isNotBlank()&&surnamee.isNotBlank()&&nameee.isNotBlank()&&middle_namee.isNotBlank()&&mentor.isNotBlank()&&group!!.isNotBlank()&&datta.isNotBlank()){
            var newStudentAdd = NewStudentAdd(surnamee,nameee,middle_namee,datta,group)
            courseDatabaseHelper.InsertNewStudent(newStudentAdd)
            finish()
        }else{
                Toast.makeText(this,"malumotlar to'liq kiritimadi", Toast.LENGTH_LONG).show()

        }
        }

        orqaga.setOnClickListener {
            finish()
        }



    }

}