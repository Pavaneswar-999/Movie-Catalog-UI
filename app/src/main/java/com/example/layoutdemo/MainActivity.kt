package com.example.layoutdemo

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var isUpdating = false
    private lateinit var tvSubtitle: TextView
    private lateinit var fragmentContainer: View
    private lateinit var spinner: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var checkboxes: List<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvSubtitle = findViewById(R.id.tv_view_subtitle)
        fragmentContainer = findViewById(R.id.fragment_container)
        spinner = findViewById(R.id.spinner)
        radioGroup = findViewById(R.id.rg_views)

        val tableCheck: CheckBox = findViewById(R.id.cb_table)
        val gridCheck: CheckBox = findViewById(R.id.cb_grid)
        val cardCheck: CheckBox = findViewById(R.id.cb_card)
        val recyclerCheck: CheckBox = findViewById(R.id.cb_recycler)
        checkboxes = listOf(tableCheck, gridCheck, cardCheck, recyclerCheck)

        // Setup Spinner using custom visible layouts
        val spinnerData = listOf(
            getString(R.string.select_view),
            getString(R.string.table_view),
            getString(R.string.grid_view),
            getString(R.string.card_view),
            getString(R.string.recycler_view)
        )
        val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, spinnerData)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        
        // Initial state: Hidden until selected
        tvSubtitle.visibility = View.GONE
        fragmentContainer.visibility = View.GONE

        // 1. RadioGroup Sync
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (isUpdating) return@setOnCheckedChangeListener
            val index = when (checkedId) {
                R.id.rb_table -> 0
                R.id.rb_grid -> 1
                R.id.rb_card -> 2
                R.id.rb_recycler -> 3
                else -> -1
            }
            if (index != -1) {
                performSync(index)
            }
        }

        // 2. Spinner Sync
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (isUpdating) return
                if (p2 == 0) {
                    tvSubtitle.visibility = View.GONE
                    fragmentContainer.visibility = View.GONE
                    radioGroup.clearCheck()
                    checkboxes.forEach { it.isChecked = false }
                    return
                }
                performSync(p2 - 1)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        // 3. Checkbox Sync
        checkboxes.forEachIndexed { index, cb ->
            cb.setOnClickListener {
                if (cb.isChecked) {
                    performSync(index)
                } else {
                    // Prevent unchecking the current active view via checkbox
                    cb.isChecked = true
                }
            }
        }
    }

    private fun performSync(index: Int) {
        if (isUpdating) return
        isUpdating = true

        // Sync RadioGroup
        val rbId = when (index) {
            0 -> R.id.rb_table
            1 -> R.id.rb_grid
            2 -> R.id.rb_card
            else -> R.id.rb_recycler
        }
        radioGroup.check(rbId)

        // Sync Spinner
        spinner.setSelection(index + 1)

        // Sync Checkboxes
        checkboxes.forEachIndexed { i, cb ->
            cb.isChecked = (i == index)
        }

        // Update Content
        updateView(index)
        
        isUpdating = false
    }

    private fun updateView(index: Int) {
        tvSubtitle.visibility = View.VISIBLE
        fragmentContainer.visibility = View.VISIBLE

        val fragment: Fragment = when (index) {
            0 -> { 
                tvSubtitle.setText(R.string.movie_table_title)
                TableFragment() 
            }
            1 -> { 
                tvSubtitle.setText(R.string.movie_grid_title)
                GridFragment() 
            }
            2 -> { 
                tvSubtitle.setText(R.string.movie_card_title)
                CardFragment() 
            }
            else -> { 
                tvSubtitle.setText(R.string.movie_recycler_title)
                RecyclerFragment() 
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
