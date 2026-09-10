package com.example.layoutdemo

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class GridFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_grid, container, false)
        val gridLayout = root.findViewById<GridLayout>(R.id.gridLayout)

        gridLayout.removeAllViews()
        gridLayout.columnCount = 2
        gridLayout.alignmentMode = GridLayout.ALIGN_BOUNDS
        
        val context = requireContext()
        
        // Create 5 rows x 2 columns = 10 movie cards
        MovieData.movies.forEachIndexed { index, movie ->
            val row = index / 2
            val col = index % 2
            
            // Create a card container for each movie
            val cardContainer = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
                gravity = android.view.Gravity.CENTER_HORIZONTAL
                setBackgroundResource(R.color.surface)
            }
            
            // Poster Image
            val poster = ShapeableImageView(context).apply {
                setImageResource(movie.resid)
                scaleType = ImageView.ScaleType.CENTER_CROP
                shapeAppearanceModel = ShapeAppearanceModel.builder()
                    .setAllCorners(CornerFamily.ROUNDED, (12 * context.resources.displayMetrics.density))
                    .build()
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (200 * context.resources.displayMetrics.density).toInt()
                ).apply {
                    bottomMargin = (8 * context.resources.displayMetrics.density).toInt()
                }
            }
            cardContainer.addView(poster)
            
            // Movie Name
            val name = TextView(context).apply {
                text = movie.movieName
                textSize = 16f
                setTypeface(null, Typeface.BOLD)
                setTextColor(ContextCompat.getColor(context, R.color.onSurface))
                gravity = android.view.Gravity.CENTER
                setPadding(8, 0, 8, 4)
                maxLines = 1
                ellipsize = android.text.TextUtils.TruncateAt.END
            }
            cardContainer.addView(name)
            
            // Year
            val year = TextView(context).apply {
                text = movie.year.toString()
                textSize = 14f
                setTextColor(ContextCompat.getColor(context, R.color.onSurfaceVariant))
                gravity = android.view.Gravity.CENTER
                setPadding(8, 0, 8, 0)
            }
            cardContainer.addView(year)
            
            // Add card to grid
            val params = GridLayout.LayoutParams().apply {
                rowSpec = GridLayout.spec(row)
                columnSpec = GridLayout.spec(col, 1f)
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                setMargins(8, 8, 8, 8)
                setGravity(android.view.Gravity.FILL)
            }
            gridLayout.addView(cardContainer, params)
        }

        return root
    }
}
