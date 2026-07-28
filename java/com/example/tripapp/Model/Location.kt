package com.example.tripapp.Model

class Location (
    val Id: Int=0,
    val Name: String=""
){
    override fun toString(): String {
        return  Name
    }
}
