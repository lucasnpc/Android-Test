package com.example.androidtest.ui.document

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtest.data.DocumentRepository
import java.lang.IllegalArgumentException

class DocumentViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DocumentViewModel::class.java)) {
            return DocumentViewModel(documentRepository = DocumentRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewlModel Class")
    }
}