package com.example.educationsysem

import Adapters.MentorsAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_adroid_kours_add.*
import kotlinx.android.synthetic.main.activity_all_student_list.*
import kotlinx.android.synthetic.main.activity_definition_course.*
import kotlinx.android.synthetic.main.activity_mentors_add.*
import kotlinx.android.synthetic.main.activity_open_and_opened_group.*
import kotlinx.android.synthetic.main.delete_student.*
import kotlinx.android.synthetic.main.item_mentor_change_dialog.*
import kotlinx.android.synthetic.main.item_mentor_change_dialog.view.*
import modul.Course
import modul.Mentor

class MentorsAdd : AppCompatActivity() {
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var mentorList:ArrayList<Mentor>
    lateinit var mentorListt:ArrayList<Mentor>
    lateinit var mentorsAdapter: MentorsAdapter
   lateinit var intentName:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentors_add)
        back_mentors_add.setOnClickListener {
            finish()
        }
        var course = intent.getSerializableExtra("course") as Course
        cours_title.text = course.courseName
        intentName = course.courseName.toString()
      courseDatabaseHelper = CourseDatabaseHelper(this)

        mentorListt= ArrayList()
        mentorList = ArrayList()

        add_Mentor_img.setOnClickListener {
            var intent = Intent(this,MentorsEdit::class.java)
            intent.putExtra("nameee",cours_title.text)
            startActivity(intent)
        }


        mentorsAdapter = MentorsAdapter(mentorList,object :MentorsAdapter.OnClickListener{
            override fun editClick(mentors: Mentor, position: Int) {
                var alertDialog = AlertDialog.Builder(this@MentorsAdd)
                alertDialog.setView(R.layout.item_mentor_change_dialog)
                var dialog = alertDialog.show()
                dialog.item_chamge_mentor_name.setText(mentors.mentorName)
                dialog.item_change_mentor_lastName.setText(mentors.mentorLastname)
                dialog.item_change_mentor_father.setText(mentors.mentorsFather)
                dialog.change_btn.setOnClickListener {
                    mentors.mentorName = dialog.item_chamge_mentor_name.text.toString()
                    mentors.mentorLastname = dialog.item_change_mentor_lastName.text.toString()
                    mentors.mentorsFather =dialog. item_change_mentor_father.text.toString()
                    courseDatabaseHelper.updateMentor(mentors)
                    mentorsAdapter.notifyItemRangeChanged(position,mentorList.size)
                    dialog.dismiss()
                }
                dialog.close_btn.setOnClickListener {
                    dialog.dismiss()
                }

            }

            override fun deleteClick(mentors: Mentor, position: Int) {
                 courseDatabaseHelper.deleteMentor(mentors)
                mentorList.remove(mentors)
                mentorsAdapter.notifyItemRemoved(position)
                mentorsAdapter.notifyItemRangeChanged(position,mentorList.size)

            }
        })


    }

    override fun onResume() {
        mentorListt= ArrayList()
        mentorList = ArrayList()
        mentorList= courseDatabaseHelper.getAllMentor()
        for (i in 0 until mentorList.size){
            if (mentorList[i].courseName == intentName) {
                mentorListt.add(mentorList[i])
            }
        }
        mentorsAdapter.setAdapter(mentorListt)
        add_mentor_rv.adapter = mentorsAdapter
        mentorsAdapter.notifyDataSetChanged()

        super.onResume()



    }
}