package com.simple.mvi.features.character

import com.simple.data.managers.CharactersManager
import com.simple.mvi.common.BaseViewModel
import com.simple.mvi.features.home.reduce
import javax.inject.Inject

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
class CharacterViewModel @Inject constructor(private val dataManager: CharactersManager) :
    BaseViewModel<CharacterIntent, CharacterAction, CharacterState >() {
    override fun intentToAction(intent: CharacterIntent): CharacterAction {
        return when (intent) {
            is CharacterIntent.LoadCharacter -> CharacterAction.LoadCharacter(intent.id)

           }
    }

    override fun handleAction(action: CharacterAction) {
        launchOnUI {
            when (action) {
                is CharacterAction.LoadCharacter -> {
                    dataManager.getCharacterDetails(action.id).collect {
                        mState.postValue(it.reduce())
                    }
                }
            }
        }
    }
}