package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var wordsList : MutableList<String> = mutableListOf()
    private lateinit var currentWord : String

    init {
        Log.d("GameFragment", "GameViewModel created")
        getNextWord()
    }
    private var _score = 0
            val score : Int
            get() = _score

    private var _currentWordCount = 0
            val currentWordCount : Int
            get() = _currentWordCount

    private lateinit var _currentScrambledWord : String
            val currentScrambledWord : String
            get() = _currentScrambledWord

    fun isUserWordCorrect(playerWord : String) : Boolean {
        return if(playerWord.equals(currentWord, true)){
            increaseScore()
            true
        } else
            false
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    override fun onCleared(){
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()

        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if(wordsList.contains(currentWord)){
            getNextWord()
        } else{
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }
    fun nextWord() : Boolean {
        return if (_currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            return true
        } else return false
    }
    fun reinitializeData(){
        _score = 0
        _currentWordCount = 0
        wordsList.clear()
        getNextWord()
    }
}
