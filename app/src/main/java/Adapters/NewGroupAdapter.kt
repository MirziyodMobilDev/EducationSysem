package Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationsysem.NewGroupAddFragment
import com.example.educationsysem.R
import kotlinx.android.synthetic.main.item_mentor.view.*
import kotlinx.android.synthetic.main.item_opened_and_open.view.*
import modul.AddMentor
import modul.GruhList


class NewGroupAdapter(var list:ArrayList<GruhList>, var onClickListener: OnClickListener): RecyclerView.Adapter<NewGroupAdapter.Vh>() {
  fun setAdapter(list: ArrayList<GruhList>){
      this.list = list
  }

    inner class Vh( itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(gruhList: GruhList, position: Int){

            itemView.item_group_name.text = gruhList.gruhName

            itemView.edit_img.setOnClickListener {
                onClickListener.editClick(gruhList, position)
            }
            itemView.delete_img.setOnClickListener {
                onClickListener.deleteClick(gruhList,position)
            }
            itemView.view_img.setOnClickListener {
                onClickListener.viewClick(gruhList,position)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewGroupAdapter.Vh {
        var itemView: View
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_opened_and_open,parent,false)
        var vh = Vh(itemView)
        return vh
    }

    override fun onBindViewHolder(holder: NewGroupAdapter.Vh, position: Int) {
        var lisPos = list[position]
        holder.onBind(lisPos,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnClickListener{
        fun editClick(gruhList: GruhList, position: Int)
        fun deleteClick(gruhList: GruhList, position: Int)
        fun viewClick(gruhList: GruhList,position: Int)

    }
}