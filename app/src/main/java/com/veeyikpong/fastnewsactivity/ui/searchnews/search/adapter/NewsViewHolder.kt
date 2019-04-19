package com.veeyikpong.fastnewsactivity.ui.searchnews.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*

class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val tvTitle = itemView.tv_title
    val tvBody = itemView.tv_body

    fun setTitle(title: String){
        tvTitle.text = title
    }

    fun setBody(body: String){
        tvBody.text = body
    }
}