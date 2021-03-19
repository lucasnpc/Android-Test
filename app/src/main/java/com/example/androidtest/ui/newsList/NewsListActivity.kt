package com.example.androidtest.ui.newsList

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

        val token = intent.getStringExtra("Token").toString()

        newsListViewModel =
            ViewModelProvider(this, NewsListViewlModelFactory()).get(NewsListViewModel::class.java)

        newsListViewModel.getNews(token = token)

        newsListViewModel.newsListState.observe(this@NewsListActivity, Observer {
            val newsListState = it ?: return@Observer

            //TODO Get List of News
            if (!newsListState.isLoading) {
                findViewById<ProgressBar>(R.id.loading_news_list).visibility = View.GONE
            }
            if (newsListState.isLoading) {
                findViewById<Button>(R.id.reload).visibility = View.GONE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.GONE
                findViewById<ProgressBar>(R.id.loading_news_list).visibility = View.VISIBLE
            }

        })

        newsListViewModel.newsListResult.observe(this@NewsListActivity, Observer {
            val newsListResult = it ?: return@Observer

            //TODO Get Request of News Result
            if (newsListResult.failed == true) {
                findViewById<Button>(R.id.reload).visibility = View.VISIBLE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.VISIBLE
            }
            if (newsListResult.success == true) {
                findViewById<Button>(R.id.reload).visibility = View.GONE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.GONE
                if (newsListResult.news != null) {
                    findViewById<RecyclerView>(R.id.news_list).apply {
                        visibility = View.VISIBLE
                        adapter = NewsListAdapter(newsListResult.news, this@NewsListActivity, token)
                    }
                }
            }
        })

        findViewById<Button>(R.id.reload).setOnClickListener {
            newsListViewModel.getNews(token = token)
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}