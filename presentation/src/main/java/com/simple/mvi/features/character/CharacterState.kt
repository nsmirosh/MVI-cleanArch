package com.simple.mvi.features.character

import com.simple.data.common.CallErrors
import com.simple.domain.entities.Persona
import com.simple.mvi.common.ViewState
import com.simple.mvi.features.home.HomeState

/**
 * Created by Rim Gazzah on 8/26/20.
 **/
sealed class CharacterState : ViewState{
    object Loading : CharacterState()
    data class ResultCharacter(val data : Persona): CharacterState()
    data class Exception(val callErrors: CallErrors) : CharacterState()

}