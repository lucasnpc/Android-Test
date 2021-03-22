package com.example.androidtest.ui.newsList.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.data.model.News
import com.example.androidtest.databinding.CardNewsListBinding
import com.example.androidtest.ui.newsInfo.NewsInfoActivity
import com.squareup.picasso.Picasso

class NewsListAdapter(
    private val newsList: ArrayList<News>,
    private val context: Context,
    private val token: String
) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    class NewsViewHolder(@NonNull val binding: CardNewsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.card_news_list ,parent, false)
        )


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        Picasso.get().load(news.imagem).into(holder.binding.imgNews)
        holder.binding.titleNews.text = news.titulo
        holder.binding.subtittleNews.text = news.linha_fina
        holder.binding.executePendingBindings()

        holder.binding.cardNews.setOnClickListener {
            updateUIWithNewsId(context, news.id_documento, token)
        }
    }

    override fun getItemCount() = newsList.size

    private fun updateUIWithNewsId(ctx: Context, id: String, token: String) {
        //TODO Update User Interface When the User Clicks on News

        ctx.startActivity(
            Intent(context, NewsInfoActivity::class.java).putExtra("id", id)
                .putExtra("token", token)
        )
    }
}