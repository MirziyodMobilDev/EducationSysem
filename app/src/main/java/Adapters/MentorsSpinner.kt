package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.educationsysem.R
import kotlinx.android.synthetic.main.spiner_item.view.*
import modul.Mentor

class MentorSpiner(var spinList:ArrayList<Mentor>): BaseAdapter() {
    override fun getCount(): Int {
        return spinList.size
    }

    override fun getItem(position: Int): Any {
        return spinList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View
        if (convertView == null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.spiner_item,parent,false)
        }else{
            itemView = convertView
        }
        itemView.spinner_edit.setText(spinList[position].mentorName)
        return itemView
    }
}
