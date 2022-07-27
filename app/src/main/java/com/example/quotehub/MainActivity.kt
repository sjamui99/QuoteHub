package com.example.quotehub

import android.accounts.AccountManager.get
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var quoteText: TextView
    lateinit var quoteAuthor: TextView
    lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteText = findViewById(R.id.quotes)
        quoteAuthor = findViewById(R.id.quoteAuthor)

        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModelFactory(application)
        ).get(QuoteViewModel::class.java)

    }


    fun setQuotes(quotes: QuotesData) {
        quoteText.text = quotes.text
        quoteAuthor.text = quotes.author
    }

    fun onPrevious(view: View) {
        setQuotes(quoteViewModel.previousQuote())
    }

    fun onNext(view: View) {
        setQuotes(quoteViewModel.nextQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, quoteViewModel.getQuote().text)
        startActivity(intent)
    }

}
