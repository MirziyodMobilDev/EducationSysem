package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsysem.MentorsAdd
import com.example.educationsysem.R
import kotlinx.android.synthetic.main.item_mentor.view.*
import modul.AddMentor
import modul.Mentor

class MentorsAdapter(var mentorList:ArrayList<Mentor>, var onClickListener: OnClickListener):
    RecyclerView.Adapter<MentorsAdapter.Vh>() {
    fun setAdapter(mentorList: ArrayList<Mentor>){
        this.mentorList = mentorList
    }


    inner class Vh( itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(mentors: Mentor, position: Int){
            itemView.item_mentor_surname.text = mentors.mentorLastname
            itemView.item_mentor_name.text = mentors.mentorName
            itemView.item_mentor_father.text = mentors.mentorsFather

            itemView.M_edit_img.setOnClickListener {
                onClickListener.editClick(mentors, position)
            }
            itemView.M_delete_img.setOnClickListener {
                onClickListener.deleteClick(mentors,position)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsAdapter.Vh {
        var itemView: View
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_mentor,parent,false)
        var vh = Vh(itemView)
        return vh
    }

    override fun onBindViewHolder(holder: MentorsAdapter.Vh, position: Int) {
        var lisPos = mentorList[position]
        holder.onBind(lisPos,position)
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }
    interface OnClickListener{
        fun editClick(mentors: Mentor, position: Int)
        fun deleteClick(mentors: Mentor, position: Int)

    }
}