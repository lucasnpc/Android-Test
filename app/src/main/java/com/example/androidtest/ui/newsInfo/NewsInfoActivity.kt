package com.example.androidtest.ui.newsInfo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R
import com.example.androidtest.databinding.ActivityNewsInfoBinding
import com.squareup.picasso.Picasso

class NewsInfoActivity : AppCompatActivity() {

    private lateinit var newsInfoViewModel: NewsInfoViewModel

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityNewsInfoBinding>(this,R.layout.activity_news_info)

        val id = intent.getStringExtra("id").toString()
        val token = intent.getStringExtra("token").toString()

        newsInfoViewModel =
            ViewModelProvider(this, NewsInfoViewModelFactory()).get(NewsInfoViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = newsInfoViewModel

        newsInfoViewModel.getNewsInfo(id = id, token = token)

        newsInfoViewModel.newsInfoResult.observe(this@NewsInfoActivity, Observer {
            val newsInfoResult = it ?: return@Observer

            //TODO Get Request of News Info Result
            if (newsInfoResult.success == true) {
                if (newsInfoResult.newsInfo != null) {
                    val nf = newsInfoResult.newsInfo.newsInfo
                    binding.titleInfo.apply {
                        text = nf.titulo
                    }
                    binding.imgInfo.apply {
                        Picasso.get().load(nf.imagem).into(this)
                    }
                    binding.creditoImg.apply {
                        text = nf.credito
                    }
                    binding.dataHoraPub.apply {
                        text = (nf.datapub + " " + nf.horapub)
                    }
                    binding.origem.apply {
                        text = nf.origem
                    }
                    binding.corpoFormatado.apply {
                        settings.javaScriptEnabled = true
                        settings.setSupportZoom(true)
                        settings.allowContentAccess = true
                        settings.defaultFontSize = 17
                        setPadding(5,0,5,0)
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

        binding.reload.setOnClickListener {
            newsInfoViewModel.getNewsInfo(id, token)
        }
    }
}