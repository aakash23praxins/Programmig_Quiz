package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.programmigquiz.database.DatabaseCopyHelper
import com.example.programmigquiz.databinding.FragmentHomeBinding

class FragmentHome : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        openAndCreateDatabase()

        binding.imgInfo.setOnClickListener {
            binding.txtMeme.visibility = View.VISIBLE
            binding.imgInfo.visibility = View.GONE
        }

        binding.btnStartQuiz.setOnClickListener {

        }

        return binding.root
    }

    private fun openAndCreateDatabase() {
        try {
            val helper = DatabaseCopyHelper(requireActivity())
            helper.createDataBase()
            helper.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}