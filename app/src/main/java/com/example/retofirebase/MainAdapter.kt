package com.example.retofirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retofirebase.databinding.PelisBinding
import org.json.JSONArray
import org.json.JSONObject

class MainAdapter(private val pelis: JSONArray): RecyclerView.Adapter<MainAdapter.MainHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = PelisBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.render(pelis[position] as JSONObject)

    }

    override fun getItemCount(): Int = pelis.length()

    class MainHolder(val binding: PelisBinding): RecyclerView.ViewHolder(binding.root){
        fun render(peli: JSONObject){
            binding.tvId.setText(peli.getString("id"))
            binding.tvTitulo.setText(peli.getString("titulo"))
            binding.tvPais.setText(peli.getString("pais"))
            binding.tvPoster.setText(peli.getString("poster"))
            binding.tvGenero.setText(peli.getString("genero"))
            binding.tvTipo.setText(peli.getString("tipo"))
            binding.tvAnio.setText(peli.getString("anio"))
            binding.tvimdbID.setText(peli.getString("imdbID"))
        }
    }
}