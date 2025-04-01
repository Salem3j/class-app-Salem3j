package com.salem.classapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlanetAdapter(private val planets: List<Planet>) :
    RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {

    class PlanetViewHolder(
        itemView: View,
        private val onPlanetClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onPlanetClicked(adapterPosition)
            }
        }

        val planetImage: ImageView = itemView.findViewById(R.id.planet_image)
        val planetName: TextView = itemView.findViewById(R.id.planet_name)
        val planetType: TextView = itemView.findViewById(R.id.planet_type)
        val planetDistance: TextView = itemView.findViewById(R.id.planet_distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.planet_card_view, parent, false)

        return PlanetViewHolder(view) { position ->
            val planet = planets[position]

            val bundle = bundleOf(
                "name" to planet.name,
                "type" to planet.type,
                "distance" to planet.distanceFromSun,
                "moons" to planet.moons,
                "image" to planet.image
            )

            val detailFragment = PlanetDetailFragment().apply {
                arguments = bundle
            }

            (view.context as AppCompatActivity).supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragment_container_view, detailFragment)
                addToBackStack(null)
            }
        }
    }

    override fun getItemCount() = planets.size

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = planets[position]
        holder.apply {
            planetName.text = itemView.context.getString(R.string.planet_name, planet.name)
            planetType.text = itemView.context.getString(R.string.planet_type, planet.type)
            planetDistance.text = itemView.context.getString(
                R.string.planet_distance,
                planet.distanceFromSun
            )

            Glide.with(itemView.context)
                .load(planet.image)
                .placeholder(R.drawable.ic_galaxy_placeholder)
                .into(planetImage)
        }
    }
}