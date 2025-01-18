package com.example.programmigquiz.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.programmigquiz.model.QuestionsModel

class QuestionDao {
    fun getRecords(helper: DatabaseCopyHelper): ArrayList<QuestionsModel> {
        val arrayList = ArrayList<QuestionsModel>()

        val database: SQLiteDatabase = helper.writableDatabase

        val cursor: Cursor = database.rawQuery("SELECT * FROM questions ORDER BY Random()", null)

        val idIndex = cursor.getColumnIndex("question_id")
        val questionTextIndex = cursor.getColumnIndex("question_text")
        val correctAnswerIndex = cursor.getColumnIndex("correct_answer")
        val optionAIndex = cursor.getColumnIndex("option_a")
        val optionBIndex = cursor.getColumnIndex("option_b")
        val optionCIndex = cursor.getColumnIndex("option_c")
        val optionDIndex = cursor.getColumnIndex("option_d")

        while (cursor.moveToNext()) {
            val record = QuestionsModel(
                cursor.getInt(idIndex),
                cursor.getString(questionTextIndex),
                cursor.getString(correctAnswerIndex),
                cursor.getString(optionAIndex),
                cursor.getString(optionBIndex),
                cursor.getString(optionCIndex),
                cursor.getString(optionDIndex),
            )
            arrayList.add(record)
        }
        cursor.close()
        return arrayList
    }
}