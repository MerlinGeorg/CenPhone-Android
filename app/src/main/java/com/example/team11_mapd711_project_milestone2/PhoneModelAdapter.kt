package com.example.team11_mapd711_project_milestone2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneModelAdapter(private val phoneModels: List<PhoneModel>,
                        private val onBuyNowClick: (PhoneModel) -> Unit) :
    RecyclerView.Adapter<PhoneModelAdapter.PhoneModelViewHolder>(){

        inner class PhoneModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val imageView: ImageView = itemView.findViewById(R.id.modelImageView)
            val nameTextView: TextView = itemView.findViewById(R.id.modelNameTextView)
            val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
            val buyNowButton: Button = itemView.findViewById(R.id.buyNowButton)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneModelViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_phone_model, parent, false)
            return PhoneModelViewHolder(view)
        }

        override fun onBindViewHolder(holder: PhoneModelViewHolder, position: Int) {
            val phoneModel = phoneModels[position]
            holder.imageView.setImageResource(phoneModel.imageResId)
            holder.nameTextView.text = phoneModel.name
            holder.priceTextView.text = "Price: ${phoneModel.price}"
            holder.buyNowButton.setOnClickListener {
               onBuyNowClick(phoneModel) // Call the callback with the selected phone model
            }
        }

        override fun getItemCount() = phoneModels.size
}