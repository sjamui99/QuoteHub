package com.example.quotehub

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class QuoteViewModel(val context:Context):ViewModel() {

    private var quoteList: Array<QuotesData> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<QuotesData> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuotesData>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun previousQuote() = quoteList[--index]
}