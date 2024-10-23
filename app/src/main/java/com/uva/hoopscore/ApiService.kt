package com.uva.hoopscore

import com.uva.hoopscore.Modelo.Clasificacion
import com.uva.hoopscore.Modelo.Partidos
import com.uva.hoopscore.Modelo.Resultado
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Metodo para obtener las estadísticas de la clasificación
    @GET("basketball/trial/v1/es/tournaments/sr:tournament:264/live_standings.json?api_key=5j5y5a5sg27ttmne292whesx")
    suspend fun getInfoClasificacion(): Clasificacion

    // Metodo para obtener los partidos del dia
    @GET("/basketball/trial/v1/es/schedules/{fecha}/results.json?api_key=5j5y5a5sg27ttmne292whesx")
    suspend fun getPartidos(@Path("fecha") fecha: String): Partidos

    // Metodo para obtener las estadísticas del partido
    @GET("basketball/trial/v2/es/sport_events/{partido}/summary.json?api_key=5j5y5a5sg27ttmne292whesx")
    suspend fun getEstadisticasPartido(@Path("partido") partido:String): Resultado


}