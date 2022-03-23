package com.example.kevin_marvel_mvvm_clean_architecture.Util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kevin_marvel_mvvm_clean_architecture.Domain.Models.Characters
import com.example.kevin_marvel_mvvm_clean_architecture.R
import java.security.AccessControlContext

class CharListAdapter(private val context: Context, var itemList: ArrayList<Characters>):
    RecyclerView.Adapter<CharListAdapter.CharListViewHolder>()
{
    inner class CharListViewHolder(view: View): RecyclerView.ViewHolder(view){
        val charName : TextView = view.findViewById(R.id.text_marvel)
        val charImg: ImageView = view.findViewById(R.id.character_image)
        val cardCharacters: LinearLayout = view.findViewById(R.id.linearlayoutCharacters)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_recyclerview,parent,false)
            return CharListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharListViewHolder, position: Int) {
        val list = itemList[position]
        holder.charName.text = list.name
        val urlImg = "${list.thumbnail}"
        Glide.with(context).load(urlImg).into(holder.charImg)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    fun setData(charlist: ArrayList<Characters>){
        this.itemList.addAll(charlist)
    }

}