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
        val loading = findViewById<ProgressBar>(R.id.loading_news_info)
        val reload = findViewById<Button>(R.id.reload)
        val requestFailedMessage = findViewById<TextView>(R.id.request_failed_news_list)
        val title = findViewById<TextView>(R.id.title_info)
        val img = findViewById<ImageView>(R.id.img_info)
        val credito = findViewById<TextView>(R.id.credito_img)
        val pub = findViewById<TextView>(R.id.data_hora_pub)
        val origem = findViewById<TextView>(R.id.origem)
        val corpo = findViewById<WebView>(R.id.corpo_formatado)

        corpo.settings.javaScriptEnabled = true
        corpo.settings.setSupportZoom(true)
        corpo.settings.allowContentAccess = true
        corpo.settings.defaultFontSize = 17
        corpo.setPadding(5, 0, 5, 0)

        newsInfoViewModel =
            ViewModelProvider(this, NewsInfoViewModelFactory()).get(NewsInfoViewModel::class.java)

        newsInfoViewModel.getDocument(id, token)

        newsInfoViewModel.newsInfoState.observe(this@NewsInfoActivity, Observer {
            val newsInfoState = it ?: return@Observer

            //TODO Get News Info
            if (!newsInfoState.isLoading) {
                loading.visibility = View.GONE
            }
            if (newsInfoState.isLoading) {
                reload.visibility = View.GONE
                requestFailedMessage.visibility = View.GONE
                loading.visibility = View.VISIBLE
            }
        })

        newsInfoViewModel.newsInfoResult.observe(this@NewsInfoActivity, Observer {
            val newsInfoResult = it ?: return@Observer

            //TODO Get Request of News Info Result
            if (newsInfoResult.failed == true) {
                reload.visibility = View.VISIBLE
                requestFailedMessage.visibility = View.VISIBLE
            }
            if (newsInfoResult.success == true) {
                reload.visibility = View.GONE
                requestFailedMessage.visibility = View.GONE
                if (newsInfoResult.newsInfo != null) {
                    val nf = newsInfoResult.newsInfo.newsInfo
                    title.apply {
                        visibility = View.VISIBLE
                        text = nf.titulo
                    }
                    img.apply {
                        visibility = View.VISIBLE
                        Picasso.get().load(nf.imagem).into(this)
                    }
                    credito.apply {
                        visibility = View.VISIBLE
                        text = nf.credito
                    }
                    pub.apply {
                        visibility = View.VISIBLE
                        text = (nf.datapub + " " + nf.horapub)
                    }
                    origem.apply {
                        visibility = View.VISIBLE
                        text = nf.origem
                    }
                    corpo.apply {
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

        reload.setOnClickListener {
            newsInfoViewModel.getDocument(id, token)
        }
    }
}