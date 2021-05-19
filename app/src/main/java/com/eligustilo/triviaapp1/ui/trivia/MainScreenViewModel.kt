package com.eligustilo.triviaapp1.ui.trivia

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {
    private var TAG = "MainScreenViewModel"
    val mutableDifficultyData = MutableLiveData<Int>()
    val mutableCategoryData = MutableLiveData<Int>()
    val mutableNumberOfQuestion = MutableLiveData<Int>()
    val sharedPreferences: SharedPreferences =  application.getSharedPreferences("DefaultPreferences", 0)
    val editablePrefrences = sharedPreferences.edit()

    init {
        getDifficulty()
        getCategory()
        getNumberOfQuestions()
    }

    fun getDifficulty (){
        val difficulty = sharedPreferences.getInt("difficulty", 1)
        mutableDifficultyData.value = difficulty
    }

    fun getCategory (){
        val getCategory = sharedPreferences.getInt("category", 0)
        mutableCategoryData.value = getCategory
    }

    fun getNumberOfQuestions(){
        val getNumberOfQuestions = sharedPreferences.getInt("numberOfQuestions", 0)
        mutableNumberOfQuestion.value = getNumberOfQuestions
        Log.d(TAG, "$getNumberOfQuestions")

    }

    fun setDifficulty (newDifficulty: Int){
        editablePrefrences.putInt("difficulty", newDifficulty)
        editablePrefrences.apply()//save it

    }

    fun setCategory (newCategory: Int){
        editablePrefrences.putInt("category", newCategory)
        editablePrefrences.apply()//save it
    }

    fun setNumberOfQuestions (newNumberOfQuestions: Int){
        editablePrefrences.putInt("numberOfQuestions", newNumberOfQuestions)
        Log.d(TAG, "$newNumberOfQuestions")
        editablePrefrences.apply()//save it
    }
}