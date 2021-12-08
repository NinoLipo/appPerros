package com.example.app_perros

import com.google.gson.annotations.SerializedName

data class RespuestaPerro( /*La implementación que hicimos nos permite convertir
Json a un objeto de tipo de dato creado por nosotros*/

    @SerializedName("status") var status: String,
    @SerializedName("message") var imagenes:List<String>)


