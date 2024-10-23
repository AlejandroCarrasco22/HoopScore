package com.uva.hoopscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameStatsActivity : AppCompatActivity() {
    private val urlBase = "https://api.sportradar.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_stats)

        val estadisticas: Button = findViewById(R.id.buttonStatsIndividuales)
        val iconoUser : ImageView = findViewById(R.id.user)
        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txtTitulo)

        // Se obtiene el id del partido que se ha marcado a través de la información extra del Intent
        val idPartido = intent.getStringExtra("idPartido")

        // Componentes de la interfaz que van a cambiar de forma dinámica, dependiendo de la información de la API

        val imagenEquipoLocal: ImageView = findViewById(R.id.imagenEquipoLocal)
        val imagenEquipoVisitante: ImageView = findViewById(R.id.imagenEquipoVisitante)
        val imagenPequenaEquipoLocal: ImageView = findViewById(R.id.imagenPequeñaEquipoLocal)
        val imagenPequenaEquipoVisitante: ImageView = findViewById(R.id.imagenPequeñaEquipoVisitante)
        val tituloEquipoLocal: TextView = findViewById(R.id.textNombreLocal)
        val tituloEquipoVisitante: TextView = findViewById(R.id.textNombreVisitante)
        val tituloEquipoLocal2: TextView = findViewById(R.id.textNombreLocal2)
        val tituloEquipoVisitante2: TextView = findViewById(R.id.textNombreVisitante2)
        val textViewPuntosLocal: List<TextView> = listOf(
            findViewById(R.id.textPuntosCuarto1Local),
            findViewById(R.id.textPuntosCuarto2Local),
            findViewById(R.id.textPuntosCuarto3Local),
            findViewById(R.id.textPuntosCuarto4Local),
            findViewById(R.id.textPuntosProrrogaLocal)
        )
        val textViewPuntosVisitante: List<TextView> = listOf(
            findViewById(R.id.textPuntosCuarto1Visitante),
            findViewById(R.id.textPuntosCuarto2Visitante),
            findViewById(R.id.textPuntosCuarto3Visitante),
            findViewById(R.id.textPuntosCuarto4Visitante),
            findViewById(R.id.textPuntosProrrogaVisitante)
        )

        val puntosTotalLocal: TextView = findViewById(R.id.textPuntosTotalLocal)
        val puntosTotalVisitante: TextView = findViewById(R.id.textPuntosTotalVisitante)
        val marcadorLocal: TextView = findViewById(R.id.textPuntuacionLocal)
        val marcadorVisitante: TextView = findViewById(R.id.textPuntuacionVisitante)

        val tirosCampoLocal: TextView = findViewById(R.id.textTCEquipoLocal)
        val porcentajeTirosCampoLocal: TextView = findViewById(R.id.textPorcentajeTCEquipoLocal)
        val tirosCampoVisitante: TextView = findViewById(R.id.textTCEquipoVisitante)
        val porcentajeTirosCampoVisitante: TextView = findViewById(R.id.textPorcentajeTCEquipoVisitante)

        val tirosDosLocal: TextView = findViewById(R.id.textTirosDosEquipoLocal)
        val porcentajeTirosDosLocal: TextView = findViewById(R.id.textPorcentajeTirosDosEquipoLocal)
        val tirosDosVisitante: TextView = findViewById(R.id.textTirosDosEquipoVisitante)
        val porcentajeTirosDosVisitante: TextView = findViewById(R.id.textPorcentajeTirosDosEquipoVisitante)

        val tirosTresLocal: TextView = findViewById(R.id.textTirosTresEquipoLocal)
        val porcentajeTirosTresLocal: TextView = findViewById(R.id.textPorcentajeTirosTresEquipoLocal)
        val tirosTresVisitante: TextView = findViewById(R.id.textTirosTresEquipoVisitante)
        val porcentajeTirosTresVisitante: TextView = findViewById(R.id.textPorcentajeTirosTresEquipoVisitante)

        val tirosLibresLocal: TextView = findViewById(R.id.textTirosLibresEquipoLocal)
        val porcentajeTirosLibresLocal: TextView = findViewById(R.id.textPorcentajeTLEquipoLocal)
        val tirosLibresVisitante: TextView = findViewById(R.id.textTirosLibresEquipoVisitante)
        val porcentajeTirosLibresVisitante: TextView = findViewById(R.id.textPorcentajeTLEquipoVisitante)

        val rebotesLocal: TextView = findViewById(R.id.textRebotesEquipoLocal)
        val rebotesVisitante: TextView = findViewById(R.id.textRebotesEquipoVisitante)

        val perdidasLocal: TextView = findViewById(R.id.textPerdidasEquipoLocal)
        val perdidasVisitante: TextView = findViewById(R.id.textPerdidasEquipoVisitante)

        val robosLocal: TextView = findViewById(R.id.textRobosEquipoLocal)
        val robosVisitante: TextView = findViewById(R.id.textRobosEquipoVisitante)

        val posesionLocal: TextView = findViewById(R.id.textPosesionEquipoLocal)
        val posesionVisitante: TextView = findViewById(R.id.textPosesionEquipoVisitante)


        // Objeto Retrofit para consumir servicios
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        // Cuando se pulsa el boton de estadísticas muestra la pantalla correspondiente
        estadisticas.setOnClickListener {
            val intent = Intent(this, PlayerStatsHomeActivity::class.java)
            intent.putExtra("idPartido", idPartido)
            startActivity(intent)
        }

        // Cuando se pulse en el icono superior (Usuario) se mostrará la pantalla User Settings
        iconoUser.setOnClickListener {
            val intent = Intent(this, UserSettingsActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el icono inferior (Jugador) se mostrará la pantalla Clasiffication
        iconoJugador.setOnClickListener {
            val intent = Intent(this, ClassificationActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el icono inferior (Casa) se mostrará la pantalla Main Page
        iconoCasa.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el nombre de la aplicación se mostrará la pantalla Main Page
        textoHoopScore.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el icono inferior (Megáfono) se mostrará la pantalla Suggestions
        iconoMegafono.setOnClickListener {
            val intent = Intent(this, SuggestionsActivity::class.java)
            startActivity(intent)
        }


        // Se realiza la llamada a la API para obtener la información necesaria
        val service = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            val response = service.getEstadisticasPartido(idPartido.toString())


            val equipos = response.statistics.totals.competitors.sortedBy { it.qualifier != "home" }
            val equipoLocal = equipos[0]
            val equipoVisitante = equipos[1]
            // Escudo del equipo local
            val drawableResId = when (equipoLocal.name) {
                "BC Andorra" -> R.drawable.andorra
                "FC Barcelona" -> R.drawable.barcelona
                "Club Joventut Badalona" -> R.drawable.badalona
                "Baskonia" -> R.drawable.baskonia
                "Bilbao Basket" -> R.drawable.bilbao
                "CB Breogán" -> R.drawable.breogan
                "Basquet Girona" -> R.drawable.girona
                "CB Gran Canaria" -> R.drawable.gran_canaria
                "Granada" -> R.drawable.granada
                "Real Madrid" -> R.drawable.madrid
                "Manresa" -> R.drawable.manresa
                "UCAM Murcia" -> R.drawable.murcia
                "Obradoiro CAB" -> R.drawable.obradoiro
                "CD Maristas Palencia" -> R.drawable.palencia
                "CB Canarias" -> R.drawable.tenerife
                "CB Málaga" -> R.drawable.unicaja
                "Valencia Basket" -> R.drawable.valencia
                "Basket Zaragoza 2002" -> R.drawable.zaragoza
                else -> R.drawable.palencia
            }
            imagenEquipoLocal.setImageResource(drawableResId)
            imagenPequenaEquipoLocal.setImageResource(drawableResId)

            // Escudo del equipo visitante
            val drawableResId2 = when (equipoVisitante.name) {
                "BC Andorra" -> R.drawable.andorra
                "FC Barcelona" -> R.drawable.barcelona
                "Club Joventut Badalona" -> R.drawable.badalona
                "Baskonia" -> R.drawable.baskonia
                "Bilbao Basket" -> R.drawable.bilbao
                "CB Breogán" -> R.drawable.breogan
                "Basquet Girona" -> R.drawable.girona
                "CB Gran Canaria" -> R.drawable.gran_canaria
                "Granada" -> R.drawable.granada
                "Real Madrid" -> R.drawable.madrid
                "Manresa" -> R.drawable.manresa
                "UCAM Murcia" -> R.drawable.murcia
                "Obradoiro CAB" -> R.drawable.obradoiro
                "CD Maristas Palencia" -> R.drawable.palencia
                "CB Canarias" -> R.drawable.tenerife
                "CB Málaga" -> R.drawable.unicaja
                "Valencia Basket" -> R.drawable.valencia
                "Basket Zaragoza 2002" -> R.drawable.zaragoza
                else -> R.drawable.palencia
            }
            imagenEquipoVisitante.setImageResource(drawableResId2)
            imagenPequenaEquipoVisitante.setImageResource(drawableResId2)


            val nombreLocal = when (equipoLocal.name) {
                "BC Andorra" -> "Morabanc Andorra"
                "FC Barcelona" -> "Barça"
                "Club Joventut Badalona" -> "Joventut Badalona"
                "Baskonia" -> "Baskonia"
                "Bilbao Basket" -> "Bilbao Basket"
                "CB Breogán" -> "Breogan"
                "Basquet Girona" -> "Bàsquet Girona"
                "CB Gran Canaria" -> "Gran Canaria"
                "Granada" -> "Covirán Granada"
                "Real Madrid" -> "Real Madrid"
                "Manresa" -> "Manresa"
                "UCAM Murcia" -> "UCAM Murcia"
                "Obradoiro CAB" -> "Obradoiro"
                "CD Maristas Palencia" -> "Zunder Palencia"
                "CB Canarias" -> "Lenovo Tenerife"
                "CB Málaga" -> "Unicaja"
                "Valencia Basket" -> "Valencia Basket"
                "Basket Zaragoza 2002" -> "Casademont Zaragoza"
                else -> "NULL"
            }
            tituloEquipoLocal.text = nombreLocal
            tituloEquipoLocal2.text = nombreLocal

            val nombreVisitante = when (equipoVisitante.name) {
                "BC Andorra" -> "Morabanc Andorra"
                "FC Barcelona" -> "Barça"
                "Club Joventut Badalona" -> "Joventut Badalona"
                "Baskonia" -> "Baskonia"
                "Bilbao Basket" -> "Bilbao Basket"
                "CB Breogán" -> "Breogan"
                "Basquet Girona" -> "Bàsquet Girona"
                "CB Gran Canaria" -> "Gran Canaria"
                "Granada" -> "Covirán Granada"
                "Real Madrid" -> "Real Madrid"
                "Manresa" -> "Manresa"
                "UCAM Murcia" -> "UCAM Murcia"
                "Obradoiro CAB" -> "Obradoiro"
                "CD Maristas Palencia" -> "Zunder Palencia"
                "CB Canarias" -> "Lenovo Tenerife"
                "CB Málaga" -> "Unicaja"
                "Valencia Basket" -> "Valencia Basket"
                "Basket Zaragoza 2002" -> "Casademont Zaragoza"
                else -> "NULL"
            }
            tituloEquipoVisitante.text = nombreVisitante
            tituloEquipoVisitante2.text = nombreVisitante


            val puntosPorCuartos = response.sport_event_status.period_scores


            // Se calcula si ha existido más de un prórroga y en ese caso se suman todos los resultados de la misma (EQUIPO LOCAL)
            var puntosProrrogaLocal = 0

            for (i in 0 until puntosPorCuartos.size){
                if (puntosPorCuartos[i].type == "overtime"){
                    puntosProrrogaLocal += puntosPorCuartos[i].home_score
                }
            }


            // Se calcula si ha existido más de un prórroga y en ese caso se suman todos los resultados de la misma (EQUIPO VISITANTE)
            var puntosProrrogaVisitante = 0

            for (i in 0 until puntosPorCuartos.size){
                if (puntosPorCuartos[i].type == "overtime"){
                    puntosProrrogaVisitante += puntosPorCuartos[i].away_score
                }
            }


            // Recorre la lista de puntos actualizando aquellos marcadores por el equipo local
            for (i in 0 until textViewPuntosLocal.size) {
                if (i < puntosPorCuartos.size) {
                    if (puntosPorCuartos[i].type == "overtime"){
                        textViewPuntosLocal[i].text = puntosProrrogaLocal.toString()
                    } else {
                        textViewPuntosLocal[i].text = puntosPorCuartos[i].home_score.toString()
                    }
                } else if (i == puntosPorCuartos.size && puntosPorCuartos.size < textViewPuntosLocal.size) {
                    textViewPuntosLocal[i].text = "-"
                } else {
                    textViewPuntosLocal[i].text = ""
                }
            }

            // Recorre la lista de puntos actualizando aquellos marcadores por el equipo visitante
            for (i in 0 until textViewPuntosVisitante.size) {
                if (i < puntosPorCuartos.size) {
                    if (puntosPorCuartos[i].type == "overtime"){
                        textViewPuntosVisitante[i].text = puntosProrrogaVisitante.toString()
                    } else {
                        textViewPuntosVisitante[i].text = puntosPorCuartos[i].away_score.toString()
                    }
                } else if (i == puntosPorCuartos.size && puntosPorCuartos.size < textViewPuntosVisitante.size) {
                    textViewPuntosVisitante[i].text = "-"
                } else {
                    textViewPuntosVisitante[i].text = ""
                }
            }


            // Se actualizan los marcadores de resultados (T) y los marcadores grandes con los puntos totales de ambos equipos
            puntosTotalLocal.text = response.sport_event_status.home_score.toString()
            puntosTotalVisitante.text = response.sport_event_status.away_score.toString()
            marcadorLocal.text = response.sport_event_status.home_score.toString()
            marcadorVisitante.text = response.sport_event_status.away_score.toString()


            // Estadísticas por equipo (Rebotes, tiros libres, triples, etc...)
            var tirosExitosos : Double
            var tirosTotales: Double
            var porcentaje: Double


            // ====Tiros de campo (dobles y triples) y su porcentaje====

            // Equipo Local
            tirosExitosos = equipos[0].statistics.two_point_attempts_successful.toDouble() + equipos[0].statistics.three_point_attempts_successful.toDouble()
            tirosTotales = equipos[0].statistics.two_point_attempts_total.toDouble() + equipos[0].statistics.three_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosCampoLocal.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosCampoLocal.text = formatearPorcentaje(porcentaje)

            // Equipo Visitante
            tirosExitosos = equipos[1].statistics.two_point_attempts_successful.toDouble() + equipos[1].statistics.three_point_attempts_successful.toDouble()
            tirosTotales = equipos[1].statistics.two_point_attempts_total.toDouble() + equipos[1].statistics.three_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosCampoVisitante.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosCampoVisitante.text = formatearPorcentaje(porcentaje)


            // ====Tiros de dos y su porcentaje====

            // Equipo Local
            tirosExitosos = equipos[0].statistics.two_point_attempts_successful.toDouble()
            tirosTotales = equipos[0].statistics.two_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosDosLocal.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosDosLocal.text = formatearPorcentaje(porcentaje)

            // Equipo Visitante
            tirosExitosos = equipos[1].statistics.two_point_attempts_successful.toDouble()
            tirosTotales = equipos[1].statistics.two_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosDosVisitante.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosDosVisitante.text = formatearPorcentaje(porcentaje)

            // ====Tiros de tres y su porcentaje====

            // Equipo Local
            tirosExitosos = equipos[0].statistics.three_point_attempts_successful.toDouble()
            tirosTotales = equipos[0].statistics.three_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosTresLocal.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosTresLocal.text = formatearPorcentaje(porcentaje)

            // Equipo Visitante
            tirosExitosos = equipos[1].statistics.three_point_attempts_successful.toDouble()
            tirosTotales = equipos[1].statistics.three_point_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosTresVisitante.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosTresVisitante.text = formatearPorcentaje(porcentaje)

            // ====Tiros libres y su porcentaje====

            // Equipo Local
            tirosExitosos = equipos[0].statistics.free_throw_attempts_successful.toDouble()
            tirosTotales = equipos[0].statistics.free_throw_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosLibresLocal.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosLibresLocal.text = formatearPorcentaje(porcentaje)

            // Equipo Visitante
            tirosExitosos = equipos[1].statistics.free_throw_attempts_successful.toDouble()
            tirosTotales = equipos[1].statistics.free_throw_attempts_total.toDouble()
            porcentaje = calcularPorcentaje(tirosExitosos,tirosTotales)
            tirosLibresVisitante.text = formatearTiros(tirosExitosos,tirosTotales)
            porcentajeTirosLibresVisitante.text = formatearPorcentaje(porcentaje)


            // ====Rebotes====

            rebotesLocal.text = equipos[0].statistics.rebounds.toString()
            rebotesVisitante.text = equipos[1].statistics.rebounds.toString()

            // ====Perdidas====

            perdidasLocal.text = equipos[0].statistics.turnovers.toString()
            perdidasVisitante.text = equipos[1].statistics.turnovers.toString()

            // ====Robos====

            robosLocal.text = equipos[0].statistics.steals.toString()
            robosVisitante.text = equipos[1].statistics.steals.toString()

            // ====Posesion====

            posesionLocal.text = equipos[0].statistics.ball_possession.toString() + "%"
            posesionVisitante.text = equipos[1].statistics.ball_possession.toString() + "%"


        }

    }

    // Calcula el porcentaje entre los tiros exitosos y los totales
    private fun calcularPorcentaje(tirosExitosos: Double, tirosTotales : Double): Double {
        return ((tirosExitosos / tirosTotales) * 100)
    }

    // Crea un formato de string de tiros exitosos frente totales
    private fun formatearTiros(tirosExitosos: Double, tirosTotales: Double) : String{
        return String.format("%.0f", tirosExitosos) + "/" + String.format("%.0f", tirosTotales)
    }

    // Crea un formato de string de el porcentaje de acierto
    private fun formatearPorcentaje(porcentaje: Double) : String{
        return String.format("%.0f", porcentaje) + "%"
    }
}