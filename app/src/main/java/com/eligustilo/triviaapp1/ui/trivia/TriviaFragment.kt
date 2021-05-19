package com.eligustilo.triviaapp1.ui.trivia

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.eligustilo.triviaapp1.R
import kotlinx.android.synthetic.main.fragment_trivia.*

class TriviaFragment : Fragment() {
    private var TAG = "TriviaFragment"

    private lateinit var triviaViewModel: TriviaViewModel
    private var numberOfTriviaAttempts = 0
    private lateinit var triviaQuestionTextView: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var scoreTextView: TextView

    private lateinit var correctAnswerInArray: String
    private var correctAnswerScoreBoard: Int = 0
    private var incorrectAnswerScoreBoard: Int = 0

    private lateinit var triviaData: TriviaData

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        triviaViewModel =
                ViewModelProviders.of(this).get(TriviaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_trivia, container, false)
        val textView: TextView = root.findViewById(R.id.text_trivia)

        triviaViewModel.mutableTriviaData.observe(viewLifecycleOwner, Observer {
            triviaData = it
            val triviaDetails = triviaData.dataArray.random()
            loadQuestion(triviaDetails)
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restartButton: Button = view.findViewById(R.id.restartButton)
        restartButton.isEnabled = false
        triviaQuestionTextView = view.findViewById(R.id.text_trivia)
        button1 = view.findViewById(R.id.answer1)
        button2 = view.findViewById(R.id.answer2)
        button3 = view.findViewById(R.id.answer3)
        button4 = view.findViewById(R.id.answer4)
        scoreTextView = view.findViewById(R.id.scoreTextView)
    }

    fun loadQuestion(triviaDetails: TrivaDetails){
        //val triviaDetails = triviaData.dataArray[numberOfTriviaAttempts]
        val triviaQuestion = triviaDetails.question
        val triviaDifficulty = triviaDetails.difficulty
        val triviaCategory = triviaDetails.category
        val triviaCorrectAnswer = triviaDetails.correct_answer
        val triviaIncorrectAnswer = triviaDetails.incorrect_answers
        Log.d(TAG, "Data is: ${triviaData.dataArray}")

        val answerArray = mutableListOf<String>(triviaCorrectAnswer, triviaIncorrectAnswer[0],triviaIncorrectAnswer[1],triviaIncorrectAnswer[2])
        answerArray.shuffle()

        Log.d(TAG, "$answerArray")

        correctAnswerInArray = htmlToString(triviaCorrectAnswer)
        triviaQuestionTextView.text = htmlToString(triviaQuestion)

        button1.text = htmlToString(answerArray[0])
//        button1.text = answerArray[0]
        button1.setOnClickListener(){
            if (answerArray[0] == correctAnswerInArray){
                val toast = Toast.makeText(this.context, "CORRECT!", Toast.LENGTH_SHORT)
                toast.show()
                correctAnswerScoreBoard = correctAnswerScoreBoard + 1
            } else {
                val toast = Toast.makeText(this.context, "FAILURE! The correct answer was $correctAnswerInArray!", Toast.LENGTH_SHORT)
                toast.show()
                incorrectAnswerScoreBoard = incorrectAnswerScoreBoard + 1
            }
            trivaGameManager()
        }

        button2.text = htmlToString(answerArray[1])
//        button2.text = answerArray[1]
        button2.setOnClickListener(){
            if (answerArray[1] == correctAnswerInArray){
                val toast = Toast.makeText(this.context, "CORRECT!", Toast.LENGTH_SHORT)
                toast.show()
                correctAnswerScoreBoard = correctAnswerScoreBoard + 1
            } else {
                val toast = Toast.makeText(this.context, "FAILURE! The correct answer was $correctAnswerInArray!", Toast.LENGTH_SHORT)
                toast.show()
                incorrectAnswerScoreBoard = incorrectAnswerScoreBoard + 1
            }
            trivaGameManager()
        }

        button3.text = htmlToString(answerArray[2])
//        button3.text = answerArray[2]
        button3.setOnClickListener(){
            if (answerArray[2] == correctAnswerInArray){
                val toast = Toast.makeText(this.context, "CORRECT!", Toast.LENGTH_SHORT)
                toast.show()
                correctAnswerScoreBoard = correctAnswerScoreBoard + 1
            } else {
                val toast = Toast.makeText(this.context, "FAILURE! The correct answer was $correctAnswerInArray!", Toast.LENGTH_SHORT)
                toast.show()
                incorrectAnswerScoreBoard = incorrectAnswerScoreBoard + 1
            }
            trivaGameManager()
        }

        button4.text = htmlToString(answerArray[3])
//        button4.text = answerArray[3]
        button4.setOnClickListener(){
            if (answerArray[3] == correctAnswerInArray){
                val toast = Toast.makeText(this.context, "CORRECT!", Toast.LENGTH_SHORT)
                toast.show()
                correctAnswerScoreBoard = correctAnswerScoreBoard + 1
            } else {
                val toast = Toast.makeText(this.context, "FAILURE! The correct answer was $correctAnswerInArray!", Toast.LENGTH_SHORT)
                toast.show()
                incorrectAnswerScoreBoard = incorrectAnswerScoreBoard + 1
            }
            trivaGameManager()
        }
    }

    fun trivaGameManager() { //this doesn't quite do what i wanted. I need a way to refresh the question without score changes.
        if (numberOfTriviaAttempts == 9){
            numberOfTriviaAttempts = 0//this was triviaIndex. Unsure of original intent for variable
            triviaViewModel.getTrivialData()
            Log.d(TAG, "TrivaIndex = $numberOfTriviaAttempts")
            scoreTextView.text = "Your Score is:$correctAnswerScoreBoard correct and $incorrectAnswerScoreBoard incorrect. Would you like to play again?"
            val toast = Toast.makeText(this.context, "Your score is !", Toast.LENGTH_SHORT)
            toast.show()
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            restartButton.isEnabled = true
            restartButton.setOnClickListener(){
                restartGame()
            }
        } else {
            numberOfTriviaAttempts = numberOfTriviaAttempts + 1
            Log.d(TAG, "TrivaIndex = $numberOfTriviaAttempts")
            val triviaDetails = triviaData.dataArray.random()
            loadQuestion(triviaDetails)
            //triviaViewModel.getTrivialData()
            loadQuestion(triviaDetails)
            scoreTextView.text = "Your Score is:$correctAnswerScoreBoard correct and $incorrectAnswerScoreBoard incorrect."
        }
    }

    fun restartGame() {
        correctAnswerScoreBoard = 0
        incorrectAnswerScoreBoard = 0
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        scoreTextView.text = "Game Reset. Your Score is:$correctAnswerScoreBoard correct and $incorrectAnswerScoreBoard incorrect."
        restartButton.isEnabled = false
    }

    fun refreshData() {
        //need to implement a refresh data here i guess?
    }

    fun htmlToString(htmlString: String): String{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            return Html.fromHtml(htmlString).toString()
        }
    }
}