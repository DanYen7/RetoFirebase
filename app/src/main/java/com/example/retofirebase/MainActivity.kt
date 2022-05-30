package com.example.retofirebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retofirebase.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.reference
        binding.btnGuardar.setOnClickListener {
            val Id = binding.edId.text.toString()
            val Title = binding.edTitulo.text.toString()
            val Year = binding.edAnio.text.toString()
            val imdbID = binding.edimdb.text.toString()
            val Type = binding.edTipo.text.toString()
            val Poster = binding.edPoster.text.toString()
            val Country = binding.edPais.text.toString()
            val Genre = binding.edGenero.text.toString()
            myRef.child("Pelis").child(Id.toString()).setValue(
                Pelis(
                    Title, Year, imdbID, Type, Poster, Country, Genre
                )
            )

            myRef.child("Pelis").get().addOnSuccessListener { response ->
                val myjson = JSONObject(response.value.toString())
                val jsonArray = JSONArray()
                val namesArray = myjson.names()
                for(i in 0..namesArray.length()-1){
                   jsonArray.put(myjson.getJSONObject(namesArray[i].toString()))
                }

                 Log.d("firebaseResponse",jsonArray.toString())
                 binding.RVPelis.adapter = MainAdapter(jsonArray)
             }.addOnFailureListener{
                 Log.e("firebaseResponse", "Error getting data", it)
             }

        }

    }
}