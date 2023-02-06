package com.simple.mvi.features.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.simple.domain.entities.Persona
import com.simple.mvi.databinding.ItemCharacterBinding


class CharactersAdapter(private val onCharClick: (Int) -> Unit) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    private var charactersList: List<Persona> = ArrayList()


    inner class CharactersViewHolder(
        binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        var _binding: ItemCharacterBinding? = null

        init {
            _binding = binding
        }

        fun bindCharacters(character: Persona) {
            with(character) {
                _binding!!.itemCharacterName.text = name
                _binding!!.itemCharacterImage.load(image)
                _binding!!.itemCharacterImage.setOnClickListener {
                    onCharClick.invoke(character.id)
                }
            }
        }
    }

   /* inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewCharacterImage: ImageView = itemView.item_character_image
        private val textViewCharacterName: TextView = itemView.item_character_name

        fun bindCharacters(character: Persona) {
            with(character) {
                textViewCharacterName.text = name
                imageViewCharacterImage.load(image)
                imageViewCharacterImage.setOnClickListener {
                    onCharClick.invoke(character.id)
                }
            }
        }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {


        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CharactersViewHolder(binding)
      /*  val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return CharactersViewHolder(itemView)*/
    }

    override fun getItemCount(): Int = charactersList.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bindCharacters(charactersList[position])
    }

    fun updateList(characterList: List<Persona>) {
        charactersList = characterList
        notifyDataSetChanged()
    }
}