package com.example.timesrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp1.model.BookDetail
import com.example.timesrecyclerview.data.BooksApi
import com.example.timesrecyclerview.model.BookDetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get a reference to the RecyclerView with id recycler_view
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        // Use a coroutine to make an HTTP request on a background thread
        lifecycleScope.launch {
            // Make a request to the BooksApi to get the best sellers list
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                // Extract the book details from the response and create a BookAdapter
                val books = response.body()?.results?.flatMap { it.book_details } ?: emptyList()
                val adapter = BookAdapter(books, object : BookAdapter.OnBookClickListener {

                    // Define what happens when a book is clicked in the adapter
                    override fun onBookClick(book: BookDetail) {

                        val intent = Intent(this@MainActivity, BookDetailActivity::class.java)

                        intent.putExtra("title", book.title)
                        intent.putExtra("description", book.description)
                        startActivity(intent)
                    }
                })

                // Set the adapter on the RecyclerView
                recyclerView.adapter = adapter
            } else {
                // If the response was not successful, log an error message
                Log.e("testing", "Failed to get best sellers")
            }
        }

    }
}