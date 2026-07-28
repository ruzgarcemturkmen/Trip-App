package com.example.tripapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tripapp.Model.Place
import com.example.tripapp.Model.Trip
import com.example.tripapp.Repository.TripsRepository

class MainViewModel (
    private val repository: TripsRepository= TripsRepository()
    ): ViewModel(){
        val upcomingTrips: LiveData<List<Trip>> = repository.getUpcomingTrips()
    val recommendedPlaces: LiveData<List<Place>> = repository.getRecommendedTrips()
    }