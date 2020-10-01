package ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.request_item.view.*
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.R
import ru.ilsave.homeworkotuscoroutinesmvvvmretrofit.network.InfoAPI

class RequestAdapter : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>(){

    inner class RequestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    //this thing is more useful than calling apapte.notifydatachange()
    private val diiferCallback = object : DiffUtil.ItemCallback<InfoAPI>(){
        override fun areItemsTheSame(oldItem: InfoAPI, newItem: InfoAPI): Boolean {
            return oldItem.participants == newItem.participants
        }

        override fun areContentsTheSame(oldItem: InfoAPI, newItem: InfoAPI): Boolean {
            return oldItem == newItem
        }

    }

    //a reference, which is able to compare two lists
    val differ = AsyncListDiffer(this, diiferCallback )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        return RequestViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.request_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        val response = differ.currentList[position]
        holder.itemView.apply {
            tv_apiName.text = response.activity
            setOnClickListener {
                onItemClickListener?.let { it(response) }
            }
        }
    }

    private var onItemClickListener: ((InfoAPI) -> Unit)? = null

    fun setOnItemClickListener(listener: (InfoAPI) -> Unit ){
        onItemClickListener = listener
    }

}