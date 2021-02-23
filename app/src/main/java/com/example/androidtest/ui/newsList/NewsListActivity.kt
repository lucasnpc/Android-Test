package com.example.androidtest.ui.newsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsListViewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        Log.e("Token", intent.getStringExtra("Token").toString())
        val loading = findViewById<ProgressBar>(R.id.loading_news_list)
        val reload = findViewById<Button>(R.id.reload)
        val requestFailedMessage = findViewById<TextView>(R.id.request_failed)

        newsListViewModel =
            ViewModelProvider(this, NewsListViewlModelFactory()).get(NewsListViewModel::class.java)

        newsListViewModel.getNews(intent.getStringExtra("Token").toString())

        newsListViewModel.newsListState.observe(this@NewsListActivity, Observer {
            val newsListState = it ?: return@Observer

            //TODO Get List of News
            if (!newsListState.isLoading) {
                loading.visibility = View.GONE
                return@Observer
            }

        })

        newsListViewModel.newsListResult.observe(this@NewsListActivity, Observer {
            val newsListResult = it ?: return@Observer

            //TODO Get Request Result
            if (newsListResult.failed == true)
            {
                reload.visibility = View.VISIBLE
                requestFailedMessage.visibility = View.VISIBLE
            }
            if (newsListResult.success == true)
            {
                reload.visibility = View.GONE
                requestFailedMessage.visibility = View.GONE
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}