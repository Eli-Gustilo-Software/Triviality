package com.eligustilo.triviaapp1.ui.trivia

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.eligustilo.triviaapp1.R


class MainScreenFragment: Fragment() {
    private var TAG = "MainScreenFragment"
    private lateinit var mainScreenViewModel: MainScreenViewModel
//    private var difficultyData by Delegates.notNull<Int>()
//    private var categoryData by Delegates.notNull<Int>()
//    private var numberOfQuestionsData by Delegates.notNull<Int>()
    private lateinit var spinnerDifficulty: Spinner
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerNoOfQuestions: Spinner
    private lateinit var startButton: Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main_screen, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainScreenViewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)

        //setting ui objects
        spinnerDifficulty = view.findViewById(R.id.mainScreenDiffcultySpinner)
        spinnerCategory = view.findViewById(R.id.mainScreenCategorySpinner)
        spinnerNoOfQuestions = view.findViewById(R.id.mainScreenNoQuestionSpinner)
        startButton = view.findViewById(R.id.startButton)


        //Spinner for difficulty
        mainScreenViewModel.mutableDifficultyData.observe(viewLifecycleOwner, Observer {
            spinnerDifficulty.setSelection(it) //TODO what does this syntax do exactly. grab the selection as it? it is the object being passed through viewmodel.
            Log.d(TAG, "$it")
        })
        spinnerDifficulty.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainScreenViewModel.setDifficulty(position)
            }
        }

        //Spinner for category
        mainScreenViewModel.mutableCategoryData.observe(viewLifecycleOwner, Observer {
            spinnerCategory.setSelection(it)
        })
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nada to be done
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainScreenViewModel.setCategory(position)
            }
        }

        //Spinner for number of questions
        mainScreenViewModel.mutableNumberOfQuestion.observe(viewLifecycleOwner, Observer {
            spinnerNoOfQuestions.setSelection(it)
        })
        spinnerNoOfQuestions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //nada to be done
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainScreenViewModel.setNumberOfQuestions(position)
            }
        }

        //Go Button
        startButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_start_to_navigation_home)
        }
    }
}