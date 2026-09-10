package com.example.layoutdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class TableFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_table, container, false)
        val tableLayout = root.findViewById<TableLayout>(R.id.tableLayout)

        tableLayout.removeAllViews()

        // Create Header Row (Name, Year, Poster)
        val context = requireContext()
        val header = TableRow(context).apply {
            setBackgroundResource(R.color.primaryContainer)
            setPadding(24, 24, 24, 24)
            gravity = android.view.Gravity.CENTER_VERTICAL
        }

        fun createHeader(title: String, weight: Float = 0f, width: Int = ViewGroup.LayoutParams.WRAP_CONTENT) = TextView(context).apply {
            text = title
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(ContextCompat.getColor(context, R.color.onPrimaryContainer))
            layoutParams = TableRow.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT, weight)
            setPadding(16, 0, 16, 0)
            gravity = android.view.Gravity.CENTER
        }

        header.addView(createHeader("Movie Name", 1f, 0))
        header.addView(createHeader("Year", 0f, 120))
        header.addView(createHeader("Poster", 0f, 120))

        tableLayout.addView(header)

        // Add All 10 unique real movies with alternating row colors
        MovieData.movies.forEachIndexed { index, movie ->
            val row = inflater.inflate(R.layout.item_movie_table, tableLayout, false) as TableRow
            
            // Alternate row background colors
            val rowBgColor = if ((index % 2) == 0) R.color.surface else R.color.surfaceVariant
            row.setBackgroundResource(rowBgColor)
            row.setPadding(24, 16, 24, 16)
            
            row.findViewById<TextView>(R.id.tv_name).apply {
                text = movie.movieName
                setTextColor(ContextCompat.getColor(context, R.color.onSurface))
            }
            row.findViewById<TextView>(R.id.tv_year).apply {
                text = movie.year.toString()
                setTextColor(ContextCompat.getColor(context, R.color.onSurfaceVariant))
            }
            row.findViewById<ImageView>(R.id.iv_poster).setImageResource(movie.resid)
            tableLayout.addView(row)
        }

        return root
    }
}
