package com.example.tripapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tripapp.Adapter.TripsAdapter.Viewholder
import com.example.tripapp.Model.Place
import com.example.tripapp.databinding.ViewholderRecommendedBinding
import com.example.tripapp.databinding.ViewholderTripBinding

class RecommendedAdapter(private val places: List<Place>): RecyclerView.Adapter<RecommendedAdapter.Viewholder>() {
   private lateinit var context: Context
    class Viewholder(val binding: ViewholderRecommendedBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderRecommendedBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedAdapter.Viewholder, position: Int) {
     val trip=places[position]

        Glide.with(context)
            .load(trip.picUrl)
            .into(holder.binding.img)

        holder.binding.titleTxt.text=trip.title
    }

    override fun getItemCount(): Int =places.size
}