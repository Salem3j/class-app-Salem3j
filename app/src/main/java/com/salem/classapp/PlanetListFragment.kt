package com.salem.classapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class PlanetListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_planet_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val planets = mutableListOf<Planet>()

        val planetNames = listOf("Mercury", "Venus", "Earth", "Mars", "Jupiter",
            "Saturn", "Uranus", "Neptune", "Kepler-22b", "Proxima Centauri b")

        val planetTypes = listOf("Terrestrial", "Gas Giant", "Ice Giant", "Super-Earth", "Dwarf Planet")

        val planetImages = listOf(
            "https://cdn.mos.cms.futurecdn.net/MTEiJvP99DScN3vkAsE9LA.jpg",
            "https://solarsystem.nasa.gov/system/resources/detail_files/608_venus_1.jpg",
            "https://www.nasa.gov/sites/default/files/thumbnails/image/pia22549-16.jpg",
            "https://www.jpl.nasa.gov/images/mars/20160421/PIA00407-16.jpg",
            "https://www.nasa.gov/sites/default/files/styles/full_width/public/thumbnails/image/pia22946-16.jpg"
        )

        for (i in 0..30) {
            planets.add(createPlanet(
                id = i,
                name = planetNames.random(),
                type = planetTypes.random(),
                distance = Random.nextDouble(0.3, 30.0),  // AU units
                moons = Random.nextInt(0, 20),
                image = planetImages.random()
            ))
        }

        val adapter = PlanetAdapter(planets)
        recyclerView.adapter = adapter

        return view
    }

    private fun createPlanet(
        id: Int,
        name: String,
        type: String,
        distance: Double,
        moons: Int,
        image: String
    ) = Planet(
        id = id,
        name = name,
        type = type,
        distanceFromSun = distance,
        moons = moons,
        image = image
    )
}