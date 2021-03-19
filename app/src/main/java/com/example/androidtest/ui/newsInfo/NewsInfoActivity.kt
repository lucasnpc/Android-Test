package com.example.androidtest.ui.newsInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R
import com.squareup.picasso.Picasso

class NewsInfoActivity : AppCompatActivity() {

    private lateinit var newsInfoViewModel: NewsInfoViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_info)

        val id = intent.getStringExtra("id").toString()
        val token = intent.getStringExtra("token").toString()

        findViewById<WebView>(R.id.corpo_formatado).apply {
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
            settings.allowContentAccess = true
            settings.defaultFontSize = 17
            setPadding(5,0,5,0)
        }

        newsInfoViewModel =
            ViewModelProvider(this, NewsInfoViewModelFactory()).get(NewsInfoViewModel::class.java)

        newsInfoViewModel.getNewsInfo(id, token)

        newsInfoViewModel.newsInfoState.observe(this@NewsInfoActivity, Observer {
            val newsInfoState = it ?: return@Observer

            //TODO Get News Info
            if (!newsInfoState.isLoading) {
                findViewById<ProgressBar>(R.id.loading_news_info).visibility = View.GONE
            }
            if (newsInfoState.isLoading) {
                findViewById<Button>(R.id.reload).visibility = View.GONE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.GONE
                findViewById<ProgressBar>(R.id.loading_news_info).visibility = View.VISIBLE
            }
        })

        newsInfoViewModel.newsInfoResult.observe(this@NewsInfoActivity, Observer {
            val newsInfoResult = it ?: return@Observer

            //TODO Get Request of News Info Result
            if (newsInfoResult.failed == true) {
                findViewById<Button>(R.id.reload).visibility = View.VISIBLE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.VISIBLE
            }
            if (newsInfoResult.success == true) {
                findViewById<Button>(R.id.reload).visibility = View.GONE
                findViewById<TextView>(R.id.request_failed_news_list).visibility = View.GONE
                if (newsInfoResult.newsInfo != null) {
                    val nf = newsInfoResult.newsInfo.newsInfo
                    findViewById<TextView>(R.id.title_info).apply {
                        visibility = View.VISIBLE
                        text = nf.titulo
                    }
                    findViewById<ImageView>(R.id.img_info).apply {
                        visibility = View.VISIBLE
                        Picasso.get().load(nf.imagem).into(this)
                    }
                    findViewById<TextView>(R.id.credito_img).apply {
                        visibility = View.VISIBLE
                        text = nf.credito
                    }
                    findViewById<TextView>(R.id.data_hora_pub).apply {
                        visibility = View.VISIBLE
                        text = (nf.datapub + " " + nf.horapub)
                    }
                    findViewById<TextView>(R.id.origem).apply {
                        visibility = View.VISIBLE
                        text = nf.origem
                    }
                    findViewById<WebView>(R.id.corpo_formatado).apply {
                        visibility = View.VISIBLE
                        loadData(
                            ("<html><style>img{width:100%;}p{text-align:justify;}.titulo{font-weight: bold;}</style><body>" +
                                    nf.corpoformatado + "</body></html>"),
                            "text/html; charset=utf-8;",
                            "utf-8"
                        )
                    }
                }
            }
        })

        findViewById<Button>(R.id.reload).setOnClickListener {
            newsInfoViewModel.getNewsInfo(id, token)
        }
    }
}