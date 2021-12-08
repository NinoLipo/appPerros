package com.example.app_perros

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    /*Quiero es esta fun tome algo y me devuelva algo.
    Retrofit se encargará automáticamete. Nosotros le decimos a Retrofit que tipo de Json queremos
    que nos traiga y Retrofit lo hace solo
    */
    @GET
    suspend fun darmePerrosDeLaRaza(@Url url: String): Response<RespuestaPerro>
    /*Función suspendible, estamos diciendo que  el main thread podría
    suspender esta función. El Main Thread siempre manda porque es el mas importante
    y según lo que necesita el main thread pueden suspenderse funciones*/
}