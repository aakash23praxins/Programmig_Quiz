package com.example.programmigquiz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.programmigquiz.R
import com.example.programmigquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        loadFragment()
    }

//    private fun loadFragment() {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.beginTransaction()
//            .add(R.id.fragmLayout, FragmentHome())
//            .commit()
//    }
}