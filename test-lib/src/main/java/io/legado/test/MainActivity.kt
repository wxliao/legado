package io.legado.test

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.legado.app.data.entities.Book
import io.legado.app.data.entities.BookSource
import io.legado.app.utils.GSON
import io.legado.test.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonTest.setOnClickListener {
            test()
        }

    }

    private fun test() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val source = getSource()
                val items = SourceHelper.getExploreItems(source)
                Log.e("liaowenxin", "items:$items")
                var firstItemUrl = ""
                for (item in items) {
                    val url = item.url
                    if (!url.isNullOrBlank()) {
                        firstItemUrl = url
                        break
                    }
                }
                val books = SourceHelper.getExploreBooks(source, firstItemUrl)
                Log.e("liaowenxin", "books:$books")
            }

        }
    }

    private fun getSource(): BookSource {
        val json = assets.open("source.json").bufferedReader().use {
            it.readText()
        }
        return GSON.fromJson(json, BookSource::class.java)
    }
}