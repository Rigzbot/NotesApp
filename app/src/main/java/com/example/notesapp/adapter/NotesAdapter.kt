package com.example.notesapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.entities.Notes
import kotlinx.android.synthetic.main.item_rv_notes.view.*
import kotlin.collections.ArrayList

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var listener:OnItemClickListener? = null
    var arrList = ArrayList<Notes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_notes,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    fun setData(arrNotesList: List<Notes>){
        arrList = arrNotesList as ArrayList<Notes>
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTitle.text = arrList[position].title
        holder.itemView.tvDesc.text = arrList[position].noteText
        holder.itemView.tvDateTime.text = arrList[position].dateTime

        //setting color of note
        if (arrList[position].color != null){
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(arrList[position].color))
        }else{
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(R.color.colorLightBlack.toString()))
        }

        //setting image of note
        if (arrList[position].imgPath != null){
            holder.itemView.imgNoteRV.setImageBitmap(BitmapFactory.decodeFile(arrList[position].imgPath))
            holder.itemView.imgNoteRV.visibility = View.VISIBLE
        }else{
            holder.itemView.imgNoteRV.visibility = View.GONE
        }

        //setting link of note
        if (arrList[position].webLink != ""){
            holder.itemView.tvWebLinkRV.text = arrList[position].webLink
            holder.itemView.tvWebLinkRV.visibility = View.VISIBLE
        }else{
            holder.itemView.tvWebLinkRV.visibility = View.GONE
        }

        holder.itemView.cardView.setOnClickListener {
            listener!!.onClicked(arrList[position].id!!)
        }

    }

    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view){

    }


    interface OnItemClickListener{
        fun onClicked(noteId:Int)
    }
}