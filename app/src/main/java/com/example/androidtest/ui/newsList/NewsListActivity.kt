package com.example.androidtest.ui.newsList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.androidtest.R
import com.example.androidtest.data.model.News
import com.example.androidtest.databinding.ActivityNewsListBinding
import com.example.androidtest.ui.newsInfo.NewsInfoActivity
import com.example.androidtest.ui.newsList.adapter.NewsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {

    private val newsListViewModel: NewsListViewModel by viewModel()
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityNewsListBinding>(
            this,
            R.layout.activity_news_list
        )

        token = intent.getStringExtra("Token").toString()

        binding.lifecycleOwner = this
        binding.viewModel = newsListViewModel

        newsListViewModel.getNews(token = token)

        newsListViewModel.newsListResult.observe(this@NewsListActivity, Observer {
            val newsListResult = it ?: return@Observer

            //TODO Get Request of News Result
            if (newsListResult.success == true) {
                if (newsListResult.news != null) {
                    binding.newsList.apply {
                        visibility = View.VISIBLE
                        adapter = NewsListAdapter(
                            newsList = newsListResult.news,
                            context = this@NewsListActivity,
                            cardItemClick = { news -> cardClick(news) })
                    }
                }
            }
        })

        binding.reload.setOnClickListener {
            newsListViewModel.getNews(token = token)
        }
    }

    private fun cardClick(news: News) {
        updateUIWithNewsId(id = news.id_documento, token = token)
    }

    private fun updateUIWithNewsId(id: String, token: String) {
        //TODO Update User Interface When the User Clicks on News
        startActivity(
            Intent(this, NewsInfoActivity::class.java).putExtra("id", id)
                .putExtra("token", token)
        )
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}