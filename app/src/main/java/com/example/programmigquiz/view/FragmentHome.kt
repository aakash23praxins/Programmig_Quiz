package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.programmigquiz.R
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
            val name = binding.edtGetName.text.toString()
            val bundle = bundleOf(
                "name" to name
            )
            if (name.isNotEmpty()){
                findNavController().apply {
                    navigate(R.id.action_fragmentHome_to_fragmentQuiz, bundle)
                }
            }else{
                Toast.makeText(requireActivity(), "Please fill name first , before starting Quiz...", Toast.LENGTH_SHORT).show()
            }
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