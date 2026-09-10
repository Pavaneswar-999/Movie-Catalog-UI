package com.example.layoutdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class RecyclerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_recycler, container, false)
        val linearLayout = root.findViewById<LinearLayout>(R.id.recyclerLayout)

        linearLayout.removeAllViews()

        val context = requireContext()
        val screenWidth = resources.displayMetrics.widthPixels
        val density = context.resources.displayMetrics.density
        val posterWidth = (screenWidth * 0.55).toInt() // ~50% of screen width
        val posterHeight = (posterWidth * 1.5).toInt() // Maintain 2:3 aspect ratio

        MovieData.movies.forEach { movie ->
            val poster = ShapeableImageView(context).apply {
                setImageResource(movie.resid)
                scaleType = ImageView.ScaleType.CENTER_CROP
                shapeAppearanceModel = ShapeAppearanceModel.builder()
                    .setAllCorners(CornerFamily.ROUNDED, 12 * density)
                    .build()
                layoutParams = LinearLayout.LayoutParams(posterWidth, posterHeight).apply {
                    setMargins((12 * density).toInt(), (12 * density).toInt(), (12 * density).toInt(), (12 * density).toInt())
                }
                setBackgroundResource(R.color.surfaceVariant)
                setPadding(2, 2, 2, 2)
            }
            linearLayout.addView(poster)
        }

        return root
    }
}
