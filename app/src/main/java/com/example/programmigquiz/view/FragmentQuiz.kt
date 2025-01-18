package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.programmigquiz.databinding.FragmentQuizBinding

class FragmentQuiz : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)

        return binding.root
    }

}