package com.salem.classapp

data class Planet(
    val id: Int,
    val name: String,
    val type: String, // Gas giant, terrestrial, etc.
    val distanceFromSun: Double, // in AU
    val moons: Int,
    val image: String
)