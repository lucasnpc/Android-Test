package com.example.androidtest.ui.document

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.R

class DocumentActivity : AppCompatActivity() {

    private lateinit var documentViewModel: DocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        documentViewModel =
            ViewModelProvider(this, DocumentViewModelFactory()).get(DocumentViewModel::class.java)
    }
}