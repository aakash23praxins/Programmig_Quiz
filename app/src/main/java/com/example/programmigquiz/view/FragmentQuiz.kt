package com.example.programmigquiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    private var isOptionChecked = false


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

        binding.btnNext.setOnClickListener {
            try {
                if (!isOptionChecked) {
                    emptyText++
                    binding.txtSkip.text = "Skip: ${emptyText}"
                }
                questionText++
                if (questionText < arrayList.size) {
                    showData()
                    initialButtonProperties()
                } else {
                    val bundle = bundleOf(
                        "correct" to correctText,
                        "wrong" to wrongText,
                        "empty" to emptyText,
                        "name" to arguments?.getString("name"),
                    )
                    findNavController().also {
                        it.navigate(R.id.action_fragmentQuiz_to_fragmentResult, bundle)
                        it.popBackStack(R.id.fragmentQuiz, true)
                    }
                    Toast.makeText(requireActivity(), "Done", Toast.LENGTH_SHORT).show()
                }
                isOptionChecked = false
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
        binding.btnOptA.text = arrayList[questionText].optionA
        binding.btnOptB.text = arrayList[questionText].optionC
        binding.btnOptC.text = arrayList[questionText].optionB
        binding.btnOptD.text = arrayList[questionText].optionD
    }

    private fun checkAnswer(button: Button) {
        val prefixes = listOf("A:", "B:", "C:", "D:")
        val buttonText = button.text.toString()
        val correctOption = arrayList[questionText].correctAnswer

        if (buttonText == correctOption) {
            button.setBackgroundColor(resources.getColor(R.color.green))
            button.setTextColor(resources.getColor(R.color.white))
            correctText++
            binding.txtCorrect.text = "Correct: ${correctText}"

        } else {
            button.setBackgroundColor(resources.getColor(R.color.red))
            button.setTextColor(resources.getColor(R.color.white))
            wrongText++
            binding.txtWrong.text = "Wrong: ${wrongText}"

            checkRightAnswer(correctOption)
        }
        binding.btnOptA.isClickable = false
        binding.btnOptB.isClickable = false
        binding.btnOptC.isClickable = false
        binding.btnOptD.isClickable = false

        isOptionChecked = true
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
    private fun checkRightAnswer(correctOption: String) {
        when (correctOption) {
            binding.btnOptA.text -> binding.btnOptA.also {
                it.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )
                it.setTextColor(resources.getColor(R.color.white, requireActivity().theme))
            }

            binding.btnOptB.text -> binding.btnOptB.also {
                it.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )
                it.setTextColor(resources.getColor(R.color.white, requireActivity().theme))
            }

            binding.btnOptC.text -> binding.btnOptC.also {
                it.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )
                it.setTextColor(resources.getColor(R.color.white, requireActivity().theme))

            }

            binding.btnOptD.text -> binding.btnOptD.also {
                it.setBackgroundColor(
                    resources.getColor(
                        R.color.green,
                        requireActivity().theme
                    )
                )
                it.setTextColor(resources.getColor(R.color.white, requireActivity().theme))
            }

        }
    }
}