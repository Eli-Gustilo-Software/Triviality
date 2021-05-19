package com.eligustilo.triviaapp1.ui.trivia

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.lang.reflect.Type

data class TriviaData(
    @SerializedName("results")
    val dataArray: ArrayList<TrivaDetails>
)

data class TrivaDetails (
    @SerializedName("category")
    val category: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("question")
    val question: String,
    @SerializedName("correct_answer")
    val correct_answer: String,
    @SerializedName("incorrect_answers")
    val incorrect_answers: ArrayList<String>
)

class TriviaParser {
    fun parseItJim(jsonData: String): TriviaData{ //: in function is a return this. In class it is a extension TODO why extend the data class?
        val gsonParser = Gson()
        val gsonDataType: Type = object: TypeToken<TriviaData>() {}.type
        val gsonResults: TriviaData = gsonParser.fromJson(jsonData, gsonDataType)
        return gsonResults
    }

//    fun customParser(jsonData: String): TriviaData{
//        val parsedTriviaData = ArrayList<TrivaDetails>()
//        val jsonObject = JSONObject(jsonData)
//
//    }
}