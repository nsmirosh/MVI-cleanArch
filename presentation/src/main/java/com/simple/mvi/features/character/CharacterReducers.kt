package com.simple.mvi.features.character

import com.simple.data.common.Result
import com.simple.domain.entities.Persona

/**
 * Created by Rim Gazzah on 8/31/20.
 **/

fun Result<Persona>.reduce(): CharacterState {
    return when (this) {
        is Result.Success -> CharacterState.ResultCharacter(data)
        is Result.Error -> CharacterState.Exception(exception)
        is Result.Loading -> CharacterState.Loading
    }
}