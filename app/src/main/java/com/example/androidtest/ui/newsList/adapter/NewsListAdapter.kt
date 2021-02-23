package com.example.androidtest.ui.newsList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.data.model.News
import com.squareup.picasso.Picasso

class NewsListAdapter(private val newsList: ArrayList<News>) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    class NewsViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_news)
        val title: TextView = itemView.findViewById(R.id.title_news)
        val subtitle: TextView = itemView.findViewById(R.id.subtittle_news)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_news_list, parent, false))


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        Picasso.get().load(news.imagem).into(holder.img)
        holder.title.text = news.titulo
        holder.subtitle.text = news.linha_fina
    }

    override fun getItemCount() = newsList.size
}