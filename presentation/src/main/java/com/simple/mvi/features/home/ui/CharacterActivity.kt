package com.simple.mvi.features.home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simple.mvi.R

class CharacterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
    }
}