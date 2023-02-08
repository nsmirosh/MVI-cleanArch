package com.simple.mvi.features.character.ui

import android.os.Bundle
import com.simple.mvi.R
import com.simple.mvi.common.BaseActivity
import com.simple.mvi.common.getMessage
import com.simple.mvi.databinding.ActivityCharacterBinding
import com.simple.mvi.databinding.ActivityMainBinding
import com.simple.mvi.features.character.CharacterAction
import com.simple.mvi.features.character.CharacterIntent
import com.simple.mvi.features.character.CharacterState
import com.simple.mvi.features.character.CharacterViewModel
import com.simple.mvi.features.home.HomeIntent

class CharacterActivity : BaseActivity<CharacterIntent, CharacterAction, CharacterState, CharacterViewModel>(CharacterViewModel::class.java) {


    private var _binding: ActivityCharacterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId() = R.layout.activity_character

    override fun initUI() {

    }

    override fun initDATA() {
        dispatchIntent(CharacterIntent.LoadCharacter(2))
    }

    override fun initEVENT() {

    }

    override fun render(state: CharacterState)  = with(state){
        when(state) {
            is CharacterState.Exception -> {
                _binding!!.tvCharacterName.text = state.callErrors.getMessage(this@CharacterActivity)
            }
            is CharacterState.ResultCharacter -> {
                _binding!!.tvCharacterName.text = state.data.name
            }
            is CharacterState.Loading -> {
                _binding!!.tvCharacterName.text = "Loading"
            }
        }
    }
}