package com.example.getrequest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.getrequest.R
import com.example.getrequest.model.Post
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            itemView.tv_userId.text = myList[position].userId.toString()
            itemView.tv_id_txt.text = myList[position].id.toString()
            itemView.tv_title_txt.text = myList[position].title
            itemView.tv_body_txt.text = myList[position].body
        }
    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}