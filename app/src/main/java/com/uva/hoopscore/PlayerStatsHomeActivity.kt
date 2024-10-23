package com.uva.hoopscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlayerStatsHomeActivity : AppCompatActivity() {

    private val urlBase = "https://api.sportradar.com/"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_stats_home)


        val resultado: Button = findViewById(R.id.buttonResultado)
        val visitante: Button = findViewById(R.id.buttonVisitante)
        val iconoUser : ImageView = findViewById(R.id.user)
        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txtTitulo)
        val idPartido = intent.getStringExtra("idPartido")

        //Varialbles de la información de la API
        val imagenEquipoLocal: ImageView = findViewById(R.id.imagenEquipoLocal)
        val imagenEquipoVisitante: ImageView = findViewById(R.id.imagenEquipoVisitante)
        val tituloEquipoLocal: TextView = findViewById(R.id.textNombreLocal)
        val tituloEquipoVisitante: TextView = findViewById(R.id.textNombreVisitante)
        val puntosTotalLocal: TextView = findViewById(R.id.textPuntuacionLocal)
        val puntosTotalVisitante: TextView = findViewById(R.id.textPuntuacionVisitante)

        val nombreJugador : List<TextView> = listOf(
            findViewById(R.id.textJugador1),
            findViewById(R.id.textJugador2),
            findViewById(R.id.textJugador3),
            findViewById(R.id.textJugador4),
            findViewById(R.id.textJugador5),
            findViewById(R.id.textJugador6),
            findViewById(R.id.textJugador7),
            findViewById(R.id.textJugador8),
            findViewById(R.id.textJugador9),
            findViewById(R.id.textJugador10),
            findViewById(R.id.textJugador11),
            findViewById(R.id.textJugador12)
        )

        val minutosJugador: List<TextView> = listOf(
            findViewById(R.id.textMinutosJugador1),
            findViewById(R.id.textMinutosJugador2),
            findViewById(R.id.textMinutosJugador3),
            findViewById(R.id.textMinutosJugador4),
            findViewById(R.id.textMinutosJugador5),
            findViewById(R.id.textMinutosJugador6),
            findViewById(R.id.textMinutosJugador7),
            findViewById(R.id.textMinutosJugador8),
            findViewById(R.id.textMinutosJugador9),
            findViewById(R.id.textMinutosJugador10),
            findViewById(R.id.textMinutosJugador11),
            findViewById(R.id.textMinutosJugador12)
            )

        val ptosJugador: List<TextView> = listOf(
            findViewById(R.id.textPtsJugador1),
            findViewById(R.id.textPtsJugador2),
            findViewById(R.id.textPtsJugador3),
            findViewById(R.id.textPtsJugador4),
            findViewById(R.id.textPtsJugador5),
            findViewById(R.id.textPtsJugador6),
            findViewById(R.id.textPtsJugador7),
            findViewById(R.id.textPtsJugador8),
            findViewById(R.id.textPtsJugador9),
            findViewById(R.id.textPtsJugador10),
            findViewById(R.id.textPtsJugador11),
            findViewById(R.id.textPtsJugador12)
            )

        val tirosJugador: List<TextView> = listOf(
            findViewById(R.id.textTCJugador1),
            findViewById(R.id.textTCJugador2),
            findViewById(R.id.textTCJugador3),
            findViewById(R.id.textTCJugador4),
            findViewById(R.id.textTCJugador5),
            findViewById(R.id.textTCJugador6),
            findViewById(R.id.textTCJugador7),
            findViewById(R.id.textTCJugador8),
            findViewById(R.id.textTCJugador9),
            findViewById(R.id.textTCJugador10),
            findViewById(R.id.textTCJugador11),
            findViewById(R.id.textTCJugador12)
            )

        val rebotesJugador: List<TextView> = listOf(
            findViewById(R.id.textRebJugador1),
            findViewById(R.id.textRebJugador2),
            findViewById(R.id.textRebJugador3),
            findViewById(R.id.textRebJugador4),
            findViewById(R.id.textRebJugador5),
            findViewById(R.id.textRebJugador6),
            findViewById(R.id.textRebJugador7),
            findViewById(R.id.textRebJugador8),
            findViewById(R.id.textRebJugador9),
            findViewById(R.id.textRebJugador10),
            findViewById(R.id.textRebJugador11),
            findViewById(R.id.textRebJugador12)
            )

        val asistenciasJugador: List<TextView> = listOf(
            findViewById(R.id.textAsistenciasJugador1),
            findViewById(R.id.textAsistenciasJugador2),
            findViewById(R.id.textAsistenciasJugador3),
            findViewById(R.id.textAsistenciasJugador4),
            findViewById(R.id.textAsistenciasJugador5),
            findViewById(R.id.textAsistenciasJugador6),
            findViewById(R.id.textAsistenciasJugador7),
            findViewById(R.id.textAsistenciasJugador8),
            findViewById(R.id.textAsistenciasJugador9),
            findViewById(R.id.textAsistenciasJugador10),
            findViewById(R.id.textAsistenciasJugador11),
            findViewById(R.id.textAsistenciasJugador12)
            )



        // Objeto Retrofit para consumir servicios
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        // Cuando se pulsa el boton Resultado se muestra la pantalla con las estadísticas del partido
        resultado.setOnClickListener {
            val intent = Intent(this, GameStatsActivity::class.java)
            intent.putExtra("idPartido", idPartido)
            startActivity(intent)
        }

        // Cuando se pulsa el boton Visitante se muestra la pantalla de estadísticas visitantes
        visitante.setOnClickListener {
            val intent = Intent(this, PlayersStatsAwayActivity::class.java)
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

        // Cuando se pulse en el nombre de la aplicación se mostrará la pantalla Main Page
        textoHoopScore.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el icono inferior (Casa) se mostrará la pantalla Main Page
        iconoCasa.setOnClickListener {
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


            val equipos = response.statistics.totals.competitors.sortedBy{ it.qualifier != "home"}
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


            // Nombre del equipo local
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


            // Nombre del equipo visitante
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

            // Puntos totales dependiendo del equipo
            puntosTotalLocal.text = response.sport_event_status.home_score.toString()
            puntosTotalVisitante.text = response.sport_event_status.away_score.toString()


            val jugadores = equipoLocal.players

            // Nombres de los jugadores de la plantilla
            nombreJugador.forEachIndexed { index, textView ->
                textView.text = jugadores[index].name
            }

            // Minutos jugados por cada jugador de la plantilla
            minutosJugador.forEachIndexed { index, textView ->
                textView.text = jugadores[index].statistics.minutes
            }

            // Puntos por cada jugador de la plantilla
            ptosJugador.forEachIndexed { index, textView ->
                textView.text = jugadores[index].statistics.points.toString()
            }

            // Tiros de campo por cada jugador de la plantilla (Acertados / Totales)
            tirosJugador.forEachIndexed { index, textView ->
                textView.text =  jugadores[index].statistics.field_goals_made.toString() + "/" + jugadores[index].statistics.field_goals_attempted.toString()
            }

            // Rebotes por cada jugador de la plantilla
            rebotesJugador.forEachIndexed { index, textView ->
                textView.text = jugadores[index].statistics.total_rebounds.toString()
            }

            // Asistencia por cada jugador de la plantilla
            asistenciasJugador.forEachIndexed { index, textView ->
                textView.text = jugadores[index].statistics.assists.toString()
            }
        }


    }
}