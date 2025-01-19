package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.programmigquiz.R
import com.example.programmigquiz.databinding.FragmentResultBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

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

        when (correct) {
            in 1..3 -> generateToast(correct)
            5 -> generateToast(correct)
            4 -> generateToast(correct)
        }

        binding.txtResultData.text = "$name You Result !!"
        val barArrayEntryCorrect = ArrayList<BarEntry>()
        val barArrayEntryWrong = ArrayList<BarEntry>()
        val barArrayEntryEmpty = ArrayList<BarEntry>()

        barArrayEntryCorrect.add(BarEntry(0f, correct!!.toFloat()))
        barArrayEntryWrong.add(BarEntry(1f, wrong!!.toFloat()))
        barArrayEntryEmpty.add(BarEntry(2f, empty!!.toFloat()))

        val dataSetCorrect = BarDataSet(barArrayEntryCorrect, "Correct").apply {
            color = resources.getColor(R.color.green, requireActivity().theme)
            valueTextSize = 24F
            setValueTextColors(arrayListOf(resources.getColor(R.color.black)))
        }
        val dataSetWrong = BarDataSet(barArrayEntryWrong, "Wrong").apply {
            color = resources.getColor(android.R.color.holo_blue_dark, requireActivity().theme)
            valueTextSize = 24F
            setValueTextColors(arrayListOf(resources.getColor(R.color.black)))
        }
        val dataSetEmpty = BarDataSet(barArrayEntryEmpty, "Empty").apply {
            color = resources.getColor(android.R.color.holo_red_dark, requireActivity().theme)
            valueTextSize = 24F
            setValueTextColors(arrayListOf(resources.getColor(R.color.black)))
        }

        binding.btnExit.setOnClickListener {
            requireActivity().finish()
        }
        binding.btnNewGame.setOnClickListener {
            findNavController().popBackStack(R.id.fragmentHome, false)
        }
        val data = BarData(dataSetCorrect, dataSetWrong, dataSetEmpty)
        binding.barChart.data = data

        return binding.root
    }

    private fun generateToast(correct: Int?) {
        Toast.makeText(requireActivity(), "You got it $correct out of 5", Toast.LENGTH_SHORT).show()
    }

}