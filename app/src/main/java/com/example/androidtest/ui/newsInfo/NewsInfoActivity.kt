package com.example.androidtest.ui.newsInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R

class NewsInfoActivity : AppCompatActivity() {

    private lateinit var newsInfoViewModel: NewsInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_info)

        val id = intent.getStringExtra("id").toString()
        val token = intent.getStringExtra("token").toString()

        newsInfoViewModel =
            ViewModelProvider(this, NewsInfoViewModelFactory()).get(NewsInfoViewModel::class.java)

        newsInfoViewModel.getDocument(id, token)

        newsInfoViewModel.newsInfoState.observe(this@NewsInfoActivity, Observer {

        })

        newsInfoViewModel.newsInfoResult.observe(this@NewsInfoActivity, Observer{

        })
    }
}