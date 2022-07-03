package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsysem.R
import kotlinx.android.synthetic.main.item_student_royhati.view.*
import modul.NewStudentAdd

class AllStudenListAdapter(var list:ArrayList<NewStudentAdd>, var onClickListener: OnClickListener):RecyclerView.Adapter<AllStudenListAdapter.Vh>() {
    fun setAdapter( list: ArrayList<NewStudentAdd>){
        this.list=list
    }
    inner class Vh(itemView: View):RecyclerView.ViewHolder(itemView){
        fun onBind(infoStudentList: NewStudentAdd,position: Int){
            itemView.item_student_name.text = infoStudentList.new_add_ism
            itemView.item_student_lastName.text = infoStudentList.new_add_familya
            itemView.item_student_father.text = infoStudentList.new_add_otasi
            itemView.S_edit_img.setOnClickListener {
                onClickListener.editClickStudent(infoStudentList,position)

            }
            itemView.S_delete_img.setOnClickListener {
                onClickListener.deleteClickStudent(infoStudentList,position)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllStudenListAdapter.Vh {
        var itemView:View
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_student_royhati,parent,false)
        var vh =Vh(itemView)
        return vh
    }

    override fun onBindViewHolder(holder: AllStudenListAdapter.Vh, position: Int) {
        var pos = list[position]
        holder.onBind(pos,position)
    }

    override fun getItemCount(): Int {
      return list.size
    }
    interface OnClickListener{
        fun editClickStudent(newStudentAdd: NewStudentAdd,position: Int)
        fun deleteClickStudent(newStudentAdd: NewStudentAdd,position: Int)
    }
}