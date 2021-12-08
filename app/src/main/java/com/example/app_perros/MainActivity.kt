package com.example.app_perros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_perros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogAdapter

    private val imagenesPerros = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*init Recycler*/
        iniciarReycleView()

        /*ListenerABusqueda*/
        binding.svRazaPerro.setOnQueryTextListener(this)
    }

    private fun buscarPorRaza(query: String) { /*Nunca podría ser null desde onQueryTextSubmit
        //Llamar a retrofit pasandole la query para que la busque (luego de haber creado una instancia de Retrofit)*/

        /*Creo un universo paralelo donde se corren un monton de cosas, distinto al Main Thread*/
        CoroutineScope(Dispatchers.IO).launch {

            val llamada = getDogRetrofit()
                    .create(ApiService::class.java)
                    .darmePerrosDeLaRaza("$query/images")

            /*Capturar resultados*/
            val respuesta: RespuestaPerro? =
                llamada.body() /*la función body() puede devolverme null*/

            /*Actualizar la vista con esos resultados*/
            runOnUiThread{
                if (llamada.isSuccessful) {
                    val imagenesACargar = respuesta?.imagenes ?: emptyList()
                    imagenesPerros.clear()
                    imagenesPerros.addAll(imagenesACargar)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        //ver la query

        //voy a validarla
        query?.let {
            if(it.isNotEmpty())
            ////buscar eso en la API
                buscarPorRaza(query.lowercase())
        }
        return true
    }

    private fun getDogRetrofit(): Retrofit{ //Devuelve instancia Retrofit

        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/") //La / al final de la URL TIENE que estar
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun iniciarReycleView() {
        //Iniciar el adaptador con la lista vacía
        adapter = DogAdapter(imagenesPerros)

        //Setear al recycler
        binding.rvImagenesPerros.layoutManager = LinearLayoutManager(this)
        binding.rvImagenesPerros.adapter = adapter
    }





    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}