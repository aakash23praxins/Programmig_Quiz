package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.programmigquiz.R
import com.example.programmigquiz.database.DatabaseCopyHelper
import com.example.programmigquiz.database.QuestionDao
import com.example.programmigquiz.databinding.FragmentQuizBinding
import com.example.programmigquiz.model.QuestionsModel

class FragmentQuiz : Fragment() {
    private lateinit var binding: FragmentQuizBinding

    private var correctText = 0
    private var wrongText = 0
    private var emptyText = 0
    private var questionText = 0

    private var arrayList = ArrayList<QuestionsModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(layoutInflater)
        val dao = QuestionDao()
        val helper = DatabaseCopyHelper(requireActivity())
        arrayList = dao.getRecords(helper)
        arrayList.shuffle()
        showData()


//        for (data in arrayList) {
//            Log.d("Questions", data.questionId.toString())
//            Log.d("Questions", data.questionText)
//            Log.d("Questions", data.optionA)
//            Log.d("Questions", data.optionB)
//            Log.d("Questions", data.optionC)
//            Log.d("Questions", data.optionD)
//            Log.d("Questions", data.correctAnswer)
//        }
        binding.btnNext.setOnClickListener {
            try {
                questionText++
                if (questionText < arrayList.size) {
                    showData()
                    initialButtonProperties()
                } else {
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                    questionText--
                }
            } catch (e: IndexOutOfBoundsException) {
                Toast.makeText(requireActivity(), "Error: Out of bounds", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnOptA.setOnClickListener {
            checkAnswer(binding.btnOptA)
        }
        binding.btnOptB.setOnClickListener {
            checkAnswer(binding.btnOptB)

        }
        binding.btnOptC.setOnClickListener {
            checkAnswer(binding.btnOptC)

        }
        binding.btnOptD.setOnClickListener {
            checkAnswer(binding.btnOptD)

        }

        return binding.root
    }

    private fun showData() {
        binding.txtQuestionNumber.text =
            resources.getString(R.string.question_1).plus(" ".plus(questionText + 1))
        binding.txtQuestion.text = arrayList[questionText].questionText
        binding.btnOptA.text = "A:" + arrayList[questionText].optionA
        binding.btnOptB.text = "B:" + arrayList[questionText].optionC
        binding.btnOptC.text = "C:" + arrayList[questionText].optionB
        binding.btnOptD.text = "D:" + arrayList[questionText].optionD
    }

    private fun checkAnswer(button: Button) {
        val prefixes = listOf("A:", "B:", "C:", "D:")
        val buttonText = button.text.toString()
        val correctOption = arrayList[questionText].correctAnswer

        val getText = prefixes.fold(buttonText) { text, prefix ->
            text.removePrefix(prefix)
        }

        println("selected $getText and correct $correctOption")
        if (getText == correctOption) {
            button.setBackgroundColor(resources.getColor(R.color.green))
            button.setTextColor(resources.getColor(R.color.white))
            correctText++
            binding.txtCorrect.text = "Correct: ${correctText}"

        } else {
            button.setBackgroundColor(resources.getColor(R.color.red))
            button.setTextColor(resources.getColor(R.color.white))
            wrongText++
            binding.txtWrong.text = "Wrong: ${wrongText}"
            when (correctOption) {
                binding.btnOptA.text.apply {
                    prefixes.fold(buttonText) { text, prefix ->
                        text.removePrefix(prefix)
                    }
                } -> binding.btnOptA.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )

                binding.btnOptB.text.apply {
                    prefixes.fold(buttonText) { text, prefix ->
                        text.removePrefix(prefix)
                    }
                } -> binding.btnOptA.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )

                binding.btnOptC.text.apply {
                    prefixes.fold(buttonText) { text, prefix ->
                        text.removePrefix(prefix)
                    }
                } -> binding.btnOptA.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )

                binding.btnOptD.text.apply {
                    prefixes.fold(buttonText) { text, prefix ->
                        text.removePrefix(prefix)
                    }
                } -> binding.btnOptA.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )
            }
        }
        binding.btnOptA.isClickable = false
        binding.btnOptB.isClickable = false
        binding.btnOptC.isClickable = false
        binding.btnOptD.isClickable = false
    }

    private fun initialButtonProperties() {
        binding.btnOptA.apply {
            setBackgroundColor(resources.getColor(R.color.white, requireActivity().theme))
            setTextColor(resources.getColor(R.color.black, requireActivity().theme))
            isClickable = true
        }
        binding.btnOptB.apply {
            setBackgroundColor(resources.getColor(R.color.white, requireActivity().theme))
            setTextColor(resources.getColor(R.color.black, requireActivity().theme))
            isClickable = true
        }
        binding.btnOptC.apply {
            setBackgroundColor(resources.getColor(R.color.white, requireActivity().theme))
            setTextColor(resources.getColor(R.color.black, requireActivity().theme))
            isClickable = true
        }
        binding.btnOptD.apply {
            setBackgroundColor(resources.getColor(R.color.white, requireActivity().theme))
            setTextColor(resources.getColor(R.color.black, requireActivity().theme))
            isClickable = true
        }
    }
}