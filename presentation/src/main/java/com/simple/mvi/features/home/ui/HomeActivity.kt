package com.simple.mvi.features.home.ui

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.simple.mvi.R
import com.simple.mvi.common.BaseActivity
import com.simple.mvi.common.getMessage
import com.simple.mvi.common.runIfTrue
import com.simple.mvi.databinding.ActivityMainBinding
import com.simple.mvi.features.character.ui.CharacterActivity
import com.simple.mvi.features.home.HomeViewModel
import com.simple.mvi.features.home.HomeAction
import com.simple.mvi.features.home.HomeIntent
import com.simple.mvi.features.home.HomeState

class HomeActivity :
    BaseActivity<HomeIntent, HomeAction, HomeState, HomeViewModel>(HomeViewModel::class.java) {

    private var _binding: ActivityMainBinding? = null

    private val mAdapter = CharactersAdapter {
        startActivity(Intent(this, CharacterActivity::class.java).apply {
            putExtra("id", it.toLong())
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        super.onCreate(savedInstanceState)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initUI() {
        _binding!!.homeListCharacters.adapter = mAdapter
    }

    override fun initDATA() {
        dispatchIntent(HomeIntent.LoadAllCharacters)
    }

    override fun initEVENT() {
        _binding!!.homeSearchImage.setOnClickListener {
            _binding!!.homeSearchText.text.isNullOrBlank().not().runIfTrue {
                dispatchIntent(HomeIntent.SearchCharacter( _binding!!.homeSearchText.text.toString()))
            }
        }
        _binding!!.homeSearchText.doOnTextChanged { text, _, _, _ ->
            text.isNullOrBlank()
                .and(mState is HomeState.ResultSearch)
                .runIfTrue {
                    dispatchIntent(HomeIntent.ClearSearch)
                }
        }
    }

    override fun render(state: HomeState) {
        _binding!!.homeProgress.isVisible = state is HomeState.Loading
        _binding!!.homeMessage.isVisible = state is HomeState.Exception
        _binding!!.homeListCharacters.isVisible =
            state is HomeState.ResultSearch || state is HomeState.ResultAllPersona

        when (state) {
            is HomeState.ResultAllPersona -> {
                mAdapter.updateList(state.data)
            }
            is HomeState.ResultSearch -> {
                mAdapter.updateList(state.data)
                // other logic ...
            }
            is HomeState.Exception -> {
                _binding!!.homeMessage.text = state.callErrors.getMessage(this)
            }
            is HomeState.Loading -> {

            }
        }
    }
}