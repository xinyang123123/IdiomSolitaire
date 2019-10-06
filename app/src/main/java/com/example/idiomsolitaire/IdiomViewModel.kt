package com.example.idiomsolitaire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idiomsolitaire.model.local.IdiomResult
import com.github.promeg.pinyinhelper.Pinyin
import kotlinx.coroutines.launch

class IdiomViewModel : ViewModel() {

    private val regexIdiom = Regex("[^\\x00-\\xff]{4,10}")
    private val regexPinyin = Regex("[a-zA-Z]{1,4}")
    private val regexChinese = Regex("[^\\x00-\\xff]")

    private val idiomRepository = IdiomRepository()

    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val searchResult: MutableLiveData<List<IdiomResult>> = MutableLiveData()

    fun search(text: String?) {
        if (verifyInput(text)) {
            viewModelScope.launch {
                val result = idiomRepository.searchIdiomByFirstPinyin(resolveChinese2Pinyin(text!!))
                searchResult.value = result
            }
        }
    }

    private fun verifyInput(text: String?): Boolean {
        if (text.isNullOrEmpty()) return false

        return if (regexIdiom.matches(text)
            || regexPinyin.matches(text)
            || regexChinese.matches(text)
        ) {
            true
        } else {
            errorMessage.value = "请输入成语、单个汉字或拼音字母"
            false
        }
    }

    private fun resolveChinese2Pinyin(text: String): String {
        return if (regexIdiom.matches(text) || regexChinese.matches(text)) {
            Pinyin.toPinyin(text.last())
        } else {
            text
        }
    }

}
