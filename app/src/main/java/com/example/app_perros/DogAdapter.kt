package com.example.app_perros
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DogAdapter(private var images: List<String>): RecyclerView.Adapter<DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_perro,parent,false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dogInPosition = images[position]
        holder.bind(dogInPosition)
    }

    override fun getItemCount() = images.size

}
