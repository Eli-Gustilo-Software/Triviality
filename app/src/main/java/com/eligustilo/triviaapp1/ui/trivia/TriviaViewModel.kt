package com.eligustilo.triviaapp1.ui.trivia

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eligustilo.triviaapp1.R

class TriviaViewModel(application: Application) : AndroidViewModel(application), DataDownloader.OnJSONReady {
    private var TAG = "TriviaViewModel"
    val mutableTriviaData = MutableLiveData<TriviaData>()
    val sharedPreferences: SharedPreferences =  application.getSharedPreferences("DefaultPreferences", 0)


    init {
        getTrivialData()
    }

    override fun jsonReady(jsonData: String) {
        val triviaParser = TriviaParser()
        val returnedTriviaParsedData = triviaParser.parseItJim(jsonData)
        //check download worked here
        //return checked valid data
        mutableTriviaData.value = returnedTriviaParsedData
    }

    fun getTrivialData() {
        val sharedPrefsDifficultyPostion = sharedPreferences.getInt("difficulty", 1)
        val sharedPrefsDifficultyArray = getApplication<Application>().resources.getStringArray(R.array.DiffcultySpinnerArray)
        val difficultyUrlValue = sharedPrefsDifficultyArray[sharedPrefsDifficultyPostion].toLowerCase()

        val sharedPrefsCategoryPosition = sharedPreferences.getInt("category", 0)
        val sharedPrefsCategoryArray = getApplication<Application>().resources.getStringArray(R.array.CategoryIDSpinnerArray)
        val categoryUrlValue = sharedPrefsCategoryArray[sharedPrefsCategoryPosition].toLowerCase()

//        val sharedPrefsNoQuestionsPostion = sharedPreferences.getInt("numberOfQuestions", 0)
//        val sharedPrefsNoQuestionsArray = getApplication<Application>().resources.getStringArray(R.array.NumberOfQuestions)
//        val noQuestionsUrlValue = sharedPrefsNoQuestionsArray[sharedPrefsNoQuestionsPostion].toLowerCase()

        val url = "https://opentdb.com/api.php?amount=500&category=$categoryUrlValue&difficulty=$difficultyUrlValue&type=multiple"
        val dataDownloader = DataDownloader(this, url)
        dataDownloader.execute()
    }
}