package com.example.layoutdemo

data class Movie(val movieName: String, val year: Int, val resid: Int)

object MovieData {
    val movies = listOf(
        Movie("It", 2017, R.drawable.it),
        Movie("Ghost", 1990, R.drawable.ghost),
        Movie("Scream", 1996, R.drawable.scream),
        Movie("Green Lantern", 2011, R.drawable.lantern),
        Movie("Crawlers", 2020, R.drawable.crawlers),
        Movie("Sinister", 2012, R.drawable.sinister),
        Movie("Evil Dead", 1981, R.drawable.evil_dead),
        Movie("Halloween", 1978, R.drawable.halloween),
        Movie("The Exorcist", 1973, R.drawable.the_exorcist),
        Movie("The Conjuring", 2013, R.drawable.the_conjuring),
    )
}
