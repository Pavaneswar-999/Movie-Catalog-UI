package com.example.layoutdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class CardFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_card, container, false)
        val containerLayout = root.findViewById<LinearLayout>(R.id.cardContainer)

        containerLayout.removeAllViews()

        MovieData.movies.forEach { movie ->
            val card = inflater.inflate(R.layout.item_movie_card, containerLayout, false)
            card.findViewById<ImageView>(R.id.iv_poster).setImageResource(movie.resid)
            card.findViewById<TextView>(R.id.tv_name).apply {
                text = movie.movieName
                setTextColor(ContextCompat.getColor(requireContext(), R.color.onSurface))
            }
            card.findViewById<TextView>(R.id.tv_year).apply {
                text = movie.year.toString()
                setTextColor(ContextCompat.getColor(requireContext(), R.color.onSurfaceVariant))
            }
            containerLayout.addView(card)
        }

        return root
    }
}
