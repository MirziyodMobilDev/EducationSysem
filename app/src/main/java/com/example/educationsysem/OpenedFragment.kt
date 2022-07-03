package com.example.educationsysem

import Adapters.NewGroupAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import db.CourseDatabaseHelper
import kotlinx.android.synthetic.main.fragment_new_group_add.view.*
import kotlinx.android.synthetic.main.fragment_opened.*
import kotlinx.android.synthetic.main.fragment_opened.view.*
import modul.GruhList


class OpenedFragment : Fragment() {
    lateinit var root:View
    lateinit var list: ArrayList<GruhList>
    lateinit var list1: ArrayList<GruhList>
    lateinit var courseDatabaseHelper: CourseDatabaseHelper
    lateinit var newGroupAdapter: NewGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        courseDatabaseHelper = CourseDatabaseHelper(container?.context!!)

       root = inflater.inflate(R.layout.fragment_opened, container, false)
        var coursee = arguments?.getString("title")
        var falsetrue = "false"
        list1 = ArrayList()
        list = ArrayList()
        list = courseDatabaseHelper.getAllGuruh()
        for (i in 0 until list.size){
            if (list[i].truthFalse ==  falsetrue){
                list1.add(list[i])
            }
            else{
                Toast.makeText(context,"${list1.size}", Toast.LENGTH_SHORT).show()
            }
        }
        newGroupAdapter = NewGroupAdapter(list1, object :NewGroupAdapter.OnClickListener{
            override fun editClick(gruhList: GruhList, position: Int) {

            }

            override fun deleteClick(gruhList: GruhList, position: Int) {

            }

            override fun viewClick(gruhList: GruhList, position: Int) {

            }
        })
        newGroupAdapter.setAdapter(list1)
        root.rv_eski.adapter = newGroupAdapter
        newGroupAdapter.notifyDataSetChanged()


        return root

    }


}