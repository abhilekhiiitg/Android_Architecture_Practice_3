package com.example.practice3.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice3.R
import com.example.practice3.R.layout
import com.example.practice3.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initialFragment()
    }

    private fun initialFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentContainer, MainFragment())
            .commit()
    }
}
