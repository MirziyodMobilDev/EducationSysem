package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsysem.R
import kotlinx.android.synthetic.main.item_course.view.*
import modul.Course

class MyAddAdapter(var courseList:ArrayList<Course>,var onClickListener:OnClickListener):RecyclerView.Adapter<MyAddAdapter.Vh>() {

    inner class Vh(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(course: Course,position: Int){
           itemView.course_name.text = course.courseName
           itemView.card.setOnClickListener {
               onClickListener.onClick(course,position)
           }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAddAdapter.Vh {
      var itemView:View
      itemView =LayoutInflater.from(parent.context).inflate(R.layout.item_course,parent,false)
        var vh = Vh(itemView)
        return vh
    }

    override fun onBindViewHolder(holder: MyAddAdapter.Vh, position: Int) {
       var listPos = courseList[position]
        holder.onBind(listPos,position)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
    interface OnClickListener{
        fun onClick(course: Course,position: Int)
    }
}