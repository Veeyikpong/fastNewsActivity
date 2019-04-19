package com.veeyikpong.fastnewsactivity.ui.searchnews.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veeyikpong.fastnewsactivity.api.response.News
import com.veeyikpong.fastnewsactivity.R

class NewsAdapter(val context: Context, var newsList: List<News>): RecyclerView.Adapter<NewsViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(news: News)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = NewsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
        v.itemView.setOnClickListener {
            if(::onItemClickListener.isInitialized){
                onItemClickListener.onItemClick(newsList[v.adapterPosition])
            }
        }
        return v
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val post = newsList[position]
        holder.setTitle(post.title)
        holder.setBody(post.body)
    }

    fun updateList(newsList: List<News>){
        this.newsList = newsList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        this.onItemClickListener = clickListener
    }
}