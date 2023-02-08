package com.simple.mvi.features.character

import com.simple.mvi.common.ViewAction

sealed class CharacterAction : ViewAction {
    data class LoadCharacter(val id: Long) : CharacterAction()
}
