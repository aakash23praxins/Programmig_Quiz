package com.example.programmigquiz.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.programmigquiz.databinding.FragmentResultBinding

class FragmentResult : Fragment() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        val name = arguments?.getString("name")
        val correct = arguments?.getInt("correct",0)
        val wrong = arguments?.getInt("wrong",0)
        val empty = arguments?.getInt("empty",0)
        Log.d("DATAA", "$name,$correct, $wrong,$empty")
        return binding.root
    }

}