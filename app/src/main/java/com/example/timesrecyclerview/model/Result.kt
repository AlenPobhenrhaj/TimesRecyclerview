package com.example.testapp1.model

data class Result(
    val amazon_product_url: String,
    val asterisk: Int,
    val bestsellers_date: String,
    val book_details: List<BookDetail>,
    val dagger: Int,
    val display_name: String,
    val isbns: List<Isbn>,
    val list_name: String,
    val published_date: String,
    val rank: Int,
    val rank_last_week: Int,
    val reviews: List<Review>,
    val weeks_on_list: Int
)