package com.example.educationsysem

import Adapters.MentorSpiner
import Adapters.NewGroupAdapter
import Adapters.TimeSpiner
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.activity_add_new_group.*
import kotlinx.android.synthetic.main.activity_add_new_group.view.*
import kotlinx.android.synthetic.main.fragment_new_group_add.view.*
import kotlinx.android.synthetic.main.item_open_group_dialog.*
import kotlinx.android.synthetic.main.item_open_group_dialog.view.*
import modul.Course
import modul.GruhList
import modul.Mentor
import modul.TimeForSpiner


class NewGroupAddFragment : Fragment() {
    lateinit var newGroupAdapter: NewGroupAdapter
    lateinit var list:ArrayList<GruhList>
    lateinit var listt:ArrayList<GruhList>
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var mentorList:ArrayList<Mentor>
    lateinit var mentorList1:ArrayList<Mentor>
    lateinit var timeList:ArrayList<TimeForSpiner>
    lateinit var timeList1:ArrayList<TimeForSpiner>
    lateinit var mentorSpiner: MentorSpiner
    lateinit var timeSpiner: TimeSpiner
    lateinit var root:View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        courseDatabaseHelper = CourseDatabaseHelper(container?.context!!)
        root = inflater.inflate(R.layout.fragment_new_group_add, container, false)
        var course = arguments?.getString("title")
        list = ArrayList()
        listt = ArrayList()
        list = courseDatabaseHelper.getAllGuruh()
        for (i in 0 until list.size){
            if (list[i].course == course.toString()){
                listt.add(list[i])
            }


        }


        newGroupAdapter = NewGroupAdapter(listt, object : NewGroupAdapter.OnClickListener {
                override fun editClick(gruhList: GruhList, position: Int) {
                    var aletrtDialog = AlertDialog.Builder(this@NewGroupAddFragment.context)
                    aletrtDialog.setView(R.layout.item_open_group_dialog)
                    var dialog = aletrtDialog.show()
                    var course = arguments?.getString("title")
                    dialog.add_course_name.setText(gruhList.gruhName)
                    mentorList = ArrayList()
                    mentorList1 = ArrayList()
                    mentorList = courseDatabaseHelper.getAllMentor()
                    for (i in 0 until mentorList.size){
                        if (mentorList[i].courseName == course){
                            mentorList1.add(mentorList[i])
                        }
                    }
                    mentorSpiner = MentorSpiner(mentorList1)
                    dialog.open_add_mentor_dialog.adapter = mentorSpiner


                  time()
                    timeSpiner = TimeSpiner(timeList)
                    dialog.open_add_data_dialog.adapter =  timeSpiner



                    dialog.edit_group.setOnClickListener {
                        var name = dialog.add_course_name.text.toString()
                        var mentor = mentorList[dialog.open_add_mentor_dialog.selectedItemPosition].mentorName
                        var time = timeList[dialog.open_add_data_dialog.selectedItemPosition].timeName
                        var gruhList = GruhList(name,mentor,time)
                        courseDatabaseHelper.update(gruhList)
                        listt[position] = gruhList
                        newGroupAdapter.notifyItemChanged(position)
                        dialog.dismiss()
                    }


                }

                override fun deleteClick(gruhList: GruhList, position: Int) {
                    courseDatabaseHelper.newGuruhDelete(gruhList)
                    listt.remove(gruhList)
                    newGroupAdapter.notifyItemRemoved(position)
                    newGroupAdapter.notifyItemRangeChanged(position, listt.size)
                }

                override fun viewClick(gruhList: GruhList, position: Int) {
                    var intent =
                        Intent(this@NewGroupAddFragment.context, AllStudentList::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("guruh", gruhList)
                    intent.putExtras(bundle)
                    intent.putExtra("id",gruhList.guruhId)
                    intent.putExtra("pos",position)
                    startActivity(intent)
                }
            })
        newGroupAdapter.setAdapter(listt)
        root.rv_new.adapter = newGroupAdapter
        newGroupAdapter.notifyDataSetChanged()
            return root

        }
    fun time(){
        timeList = ArrayList()
        timeList.add(TimeForSpiner("8.30 : 10.30"))
        timeList.add(TimeForSpiner("10.30 : 13.30"))
        timeList.add(TimeForSpiner("13.30 : 15.30"))
        timeList.add(TimeForSpiner("15.30 : 17.30"))
        timeList.add(TimeForSpiner("17.30 : 19.30"))
    }



    }


