package com.example.timesrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp1.model.BookDetail

// Define a BookAdapter class that extends RecyclerView.Adapter and takes a list of BookDetail objects and an OnBookClickListener object as parameters
class BookAdapter(private val books: List<BookDetail>, private val listener: OnBookClickListener) : RecyclerView.Adapter<BookViewHolder>() {

    // Override the onCreateViewHolder method to inflate the layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return BookViewHolder(itemView)
    }

    // Override the onBindViewHolder method to bind the data from the BookDetail object to the views in the ViewHolder
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]
        holder.titleTextView.text = book.title
        holder.itemView.setOnClickListener {
            // Call the onBookClick method of the listener object, passing in the BookDetail object
            listener.onBookClick(book)
        }
    }

    // Override the getItemCount method to return the size of the list of BookDetail objects
    override fun getItemCount() = books.size

    // Define an interface that defines a callback method for when a book is clicked in the adapter
    interface OnBookClickListener {
        fun onBookClick(book: BookDetail)
    }
}

