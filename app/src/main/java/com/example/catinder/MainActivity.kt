package com.example.catinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yalantis.library.Koloda

class MainActivity : AppCompatActivity() {
    private lateinit var swipeAdapter: SwipeAdapter
    private lateinit var list: List<Integer>
    private lateinit var koloda: Koloda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        koloda = findViewById(R.id.koloda)

        swipeAdapter = SwipeAdapter(this, list)
        koloda.adapter = adapter
    }
}