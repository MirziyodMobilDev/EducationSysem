package com.example.educationsysem

import Adapters.AllStudenListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.android.synthetic.main.activity_all_student_list.*
import kotlinx.android.synthetic.main.delete_student.*
import modul.GruhList
import modul.NewStudentAdd

class AllStudentList : AppCompatActivity() {
    lateinit var allStudenListAdapter: AllStudenListAdapter
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var list:ArrayList<NewStudentAdd>
    lateinit var list1:ArrayList<NewStudentAdd>
    lateinit var grouhList:ArrayList<GruhList>
    lateinit var intentguruhname:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_student_list)
        courseDatabaseHelper = CourseDatabaseHelper(this)


      var intentinfo = intent.getSerializableExtra("guruh")  as GruhList
        G_name.text = intentinfo.gruhName
       D_name.text = intentinfo.time
        intentguruhname = intentinfo.gruhName .toString()
        new_student_add.setOnClickListener {
            var intent = Intent(this, Add_New_Student::class.java)
            intent.putExtra("gruhname", G_name.text)
            startActivity(intent)
        }
        list = ArrayList()
        list1 = ArrayList()

        allStudenListAdapter = AllStudenListAdapter(list1,object :AllStudenListAdapter.OnClickListener{
            override fun editClickStudent(newStudentAdd: NewStudentAdd, position: Int) {
              var intent = Intent(this@AllStudentList,Change_Student::class.java)
                intent.putExtra("ID",newStudentAdd.new_add_id)
                startActivity(intent)
            }
            override fun deleteClickStudent(newStudentAdd: NewStudentAdd, position: Int) {
               var alertDialog = AlertDialog.Builder(this@AllStudentList)
                alertDialog.setView(R.layout.delete_student)
                var dialog = alertDialog.show()
                dialog.yes.setOnClickListener{
                    courseDatabaseHelper.newStudentDelete(newStudentAdd)
                    list1.remove(newStudentAdd)
                    allStudenListAdapter.notifyItemRemoved(list1.size)
                    allStudenListAdapter.notifyItemRangeChanged(position,list1.size)
                    dialog.dismiss()

                    }
                dialog.no.setOnClickListener {
                    dialog.dismiss()
                }


            }
        })
        all_S_back.setOnClickListener {
            finish()
        }
   card.setOnClickListener {
       var id = intent.getIntExtra("id",0)
       var gruh = courseDatabaseHelper.getGuruhById(id)
       var pos = intent.getIntExtra("pos",0)
       grouhList = courseDatabaseHelper.getAllGuruh()
       gruh.truthFalse = "false"
       var group = GruhList(gruh.truthFalse)
       grouhList[pos] = group
       courseDatabaseHelper.update(group)
       Toast.makeText(this,"${group.truthFalse}",Toast.LENGTH_LONG).show()

  finish()
   }



    }

    override fun onResume() {
        list = ArrayList()
        list1 = ArrayList()
        list = courseDatabaseHelper.getAllNewStudent()
        for (i in 0 until list.size){
            if (list[i].intent_groupname == intentguruhname){
                list1.add(list[i])
            }

        }
        allStudenListAdapter.setAdapter(list1)
        all_student_rv.adapter = allStudenListAdapter
        allStudenListAdapter.notifyDataSetChanged()
        allStudenListAdapter.notifyItemChanged(list1.size)
        super.onResume()
    }


}