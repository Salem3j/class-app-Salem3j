package com.salem.classapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class PlanetDetailFragment : Fragment() {

    @SuppressLint("StringFormatMatches")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_planet_detail,  // Update layout reference
            container,
            false
        )

        arguments?.let { bundle ->
            val name = bundle.getString("name")
            val type = bundle.getString("type")
            val distance = bundle.getDouble("distance")
            val moons = bundle.getInt("moons")
            val image = bundle.getString("image")

            with(view) {
                findViewById<TextView>(R.id.planet_name_detail).text =
                    resources.getString(R.string.planet_name, name)

                findViewById<TextView>(R.id.planet_type_detail).text =
                    resources.getString(R.string.planet_type, type)

                findViewById<TextView>(R.id.planet_distance_detail).text =
                    context.getString(R.string.planet_distance, distance)


                findViewById<TextView>(R.id.planet_moons_detail).text =
                    resources.getString(R.string.planet_moons, moons)

                Glide.with(this@PlanetDetailFragment)
                    .load(image)
                    .placeholder(R.drawable.ic_galaxy_placeholder)  // Update placeholder
                    .into(findViewById(R.id.planet_image_detail))
            }
        }
        return view
    }
}