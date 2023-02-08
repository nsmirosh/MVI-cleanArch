package com.simple.mvi.features.character

import com.simple.mvi.common.ViewIntent

sealed class CharacterIntent: ViewIntent {

    data class LoadCharacter(val id: Long) : CharacterIntent()
}