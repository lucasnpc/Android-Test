package com.example.androidtest.ui.newsList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityNewsListBinding
import com.example.androidtest.ui.newsList.adapter.NewsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsListActivity : AppCompatActivity() {

    private val newsListViewModel: NewsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityNewsListBinding>(this, R.layout.activity_news_list)

        val token = intent.getStringExtra("Token").toString()

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
                        adapter = NewsListAdapter(newsListResult.news, this@NewsListActivity, token)
                    }
                }
            }
        })

        binding.reload.setOnClickListener {
            newsListViewModel.getNews(token = token)
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}