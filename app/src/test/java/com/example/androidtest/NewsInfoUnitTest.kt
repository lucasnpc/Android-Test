package com.example.androidtest

import androidx.lifecycle.MutableLiveData
import com.example.androidtest.data.DocumentRepository
import com.example.androidtest.ui.newsInfo.DocumentResult
import com.example.androidtest.ui.newsInfo.DocumentState
import org.junit.Test

class NewsInfoUnitTest {
    private val _state = MutableLiveData<DocumentState>()
    private val _result = MutableLiveData<DocumentResult>()
    private val _repository = DocumentRepository()

    @Test
    fun getResult(){
        _repository.getDocument("1", _state, _result)
        assert(_result.value?.newsInfo != null)
    }

}