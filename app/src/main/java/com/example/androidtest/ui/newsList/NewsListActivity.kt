package com.example.androidtest.ui.newsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtest.R
import com.example.androidtest.ui.newsList.adapter.NewsListAdapter

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val _token = intent.getStringExtra("Token").toString()
        val loading = findViewById<ProgressBar>(R.id.loading_news_list)
        val reload = findViewById<Button>(R.id.reload)
        val requestFailedMessage = findViewById<TextView>(R.id.request_failed)
        val recyclerNewsList = findViewById<RecyclerView>(R.id.news_list)

        newsListViewModel =
            ViewModelProvider(this, NewsListViewlModelFactory()).get(NewsListViewModel::class.java)

        newsListViewModel.getNews(token = _token)

        newsListViewModel.newsListState.observe(this@NewsListActivity, Observer {
            val newsListState = it ?: return@Observer

            //TODO Get List of News
            if (!newsListState.isLoading) {
                loading.visibility = View.GONE
            }
            if (newsListState.isLoading) {
                reload.visibility = View.GONE
                requestFailedMessage.visibility = View.GONE
                loading.visibility = View.VISIBLE
            }

        })

        newsListViewModel.newsListResult.observe(this@NewsListActivity, Observer {
            val newsListResult = it ?: return@Observer

            //TODO Get Request Result
            if (newsListResult.failed == true) {
                reload.visibility = View.VISIBLE
                requestFailedMessage.visibility = View.VISIBLE
            }
            if (newsListResult.success == true) {
                reload.visibility = View.GONE
                requestFailedMessage.visibility = View.GONE
                if (newsListResult.news != null) {
                    recyclerNewsList.visibility = View.VISIBLE
                    recyclerNewsList.apply { adapter = NewsListAdapter(newsListResult.news) }
                }
            }
        })

        reload.setOnClickListener {
            newsListViewModel.getNews(token = _token)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}