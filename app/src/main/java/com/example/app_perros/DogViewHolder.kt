package com.example.app_perros

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_perros.databinding.ItemPerroBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemPerroBinding.bind(view)

    fun bind(imagen: String) {
        Log.println(Log.WARN, "Pictures list [0] url: ", imagen)
        Picasso.get().load(imagen).into(binding.ivPerro)
    }


}
