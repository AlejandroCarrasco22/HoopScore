package com.uva.hoopscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClassificationActivity : AppCompatActivity() {
    val urlBase = "https://api.sportradar.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)


        val iconoUser: ImageView = findViewById(R.id.user)
        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txtTitulo)


        // Objeto Retrofit para consumir servicios
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Lista de todos los textView de nombres de equipos de la clasificacion
        val textViewListNombres: List<TextView> = listOf(
            findViewById(R.id.textView1),
            findViewById(R.id.textView2),
            findViewById(R.id.textView3),
            findViewById(R.id.textView4),
            findViewById(R.id.textView5),
            findViewById(R.id.textView6),
            findViewById(R.id.textView7),
            findViewById(R.id.textView8),
            findViewById(R.id.textView9),
            findViewById(R.id.textView10),
            findViewById(R.id.textView11),
            findViewById(R.id.textView12),
            findViewById(R.id.textView13),
            findViewById(R.id.textView14),
            findViewById(R.id.textView15),
            findViewById(R.id.textView16),
            findViewById(R.id.textView17),
            findViewById(R.id.textView18),
        )

        // Lista de todos los textView de los partidos jugados de la clasificacion
        val textViewListJugados: List<TextView> = listOf(
            findViewById(R.id.textPartidos1),
            findViewById(R.id.textPartidos2),
            findViewById(R.id.textPartidos3),
            findViewById(R.id.textPartidos4),
            findViewById(R.id.textPartidos5),
            findViewById(R.id.textPartidos6),
            findViewById(R.id.textPartidos7),
            findViewById(R.id.textPartidos8),
            findViewById(R.id.textPartidos9),
            findViewById(R.id.textPartidos10),
            findViewById(R.id.textPartidos11),
            findViewById(R.id.textPartidos12),
            findViewById(R.id.textPartidos13),
            findViewById(R.id.textPartidos14),
            findViewById(R.id.textPartidos15),
            findViewById(R.id.textPartidos16),
            findViewById(R.id.textPartidos17),
            findViewById(R.id.textPartidos18),
        )

        // Lista de todos los textView de los partidos ganados de la clasificacion
        val textViewListGanados: List<TextView> = listOf(
            findViewById(R.id.textGanados1),
            findViewById(R.id.textGanados2),
            findViewById(R.id.textGanados3),
            findViewById(R.id.textGanados4),
            findViewById(R.id.textGanados5),
            findViewById(R.id.textGanados6),
            findViewById(R.id.textGanados7),
            findViewById(R.id.textGanados8),
            findViewById(R.id.textGanados9),
            findViewById(R.id.textGanados10),
            findViewById(R.id.textGanados11),
            findViewById(R.id.textGanados12),
            findViewById(R.id.textGanados13),
            findViewById(R.id.textGanados14),
            findViewById(R.id.textGanados15),
            findViewById(R.id.textGanados16),
            findViewById(R.id.textGanados17),
            findViewById(R.id.textGanados18),
        )


        // Lista de todos los textView de los partidos perdidos de la clasificacion
        val textViewListPerdidos: List<TextView> = listOf(
            findViewById(R.id.textPerdidos1),
            findViewById(R.id.textPerdidos2),
            findViewById(R.id.textPerdidos3),
            findViewById(R.id.textPerdidos4),
            findViewById(R.id.textPerdidos5),
            findViewById(R.id.textPerdidos6),
            findViewById(R.id.textPerdidos7),
            findViewById(R.id.textPerdidos8),
            findViewById(R.id.textPerdidos9),
            findViewById(R.id.textPerdidos10),
            findViewById(R.id.textPerdidos11),
            findViewById(R.id.textPerdidos12),
            findViewById(R.id.textPerdidos13),
            findViewById(R.id.textPerdidos14),
            findViewById(R.id.textPerdidos15),
            findViewById(R.id.textPerdidos16),
            findViewById(R.id.textPerdidos17),
            findViewById(R.id.textPerdidos18),
        )

        // Lista de todos los imageView de los escudos de equipos
        val imageViewListEscudos: List<ImageView> = listOf(
            findViewById(R.id.imageView1),
            findViewById(R.id.imageView2),
            findViewById(R.id.imageView3),
            findViewById(R.id.imageView4),
            findViewById(R.id.imageView5),
            findViewById(R.id.imageView6),
            findViewById(R.id.imageView7),
            findViewById(R.id.imageView8),
            findViewById(R.id.imageView9),
            findViewById(R.id.imageView10),
            findViewById(R.id.imageView11),
            findViewById(R.id.imageView12),
            findViewById(R.id.imageView13),
            findViewById(R.id.imageView14),
            findViewById(R.id.imageView15),
            findViewById(R.id.imageView16),
            findViewById(R.id.imageView17),
            findViewById(R.id.imageView18),
        )


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

        // Cuando se pulse en el icono inferior (Megáfono) se mostrará la pantalla Suggestions
        iconoMegafono.setOnClickListener {
            val intent = Intent(this, SuggestionsActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pulse en el nombre de la aplicación se mostrará la pantalla Main Page
        textoHoopScore.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }


        // Se realiza la llamada a la API para obtener la información necesaria
        val service = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            val response = service.getInfoClasificacion()
            val teamsRank = response.standings
                .filter { it.type == "total" }
                .flatMap { it.groups }
                .filter { it.id == 76941 }
                .flatMap { it.team_standings }
                .groupBy { it.rank }


            // Nombres de los equipos dependiendo de la clasificación
            textViewListNombres.forEachIndexed { index, textView ->
                val teams = teamsRank[index + 1]
                if (teams != null && teams.isNotEmpty()) {

                    val nombre = when (teams[0].team.name) {
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
                    textView.text = nombre
                }

                // Partidos jugados por cada uno de los equipos
                textViewListJugados.forEachIndexed { index, textView ->
                    val teams = teamsRank[index + 1]
                    if (teams != null && teams.isNotEmpty()) {
                        textView.text = teams[0].played.toString()
                    }
                }

                // Partidos ganados por cada uno de los equipos
                textViewListGanados.forEachIndexed { index, textView ->
                    val teams = teamsRank[index + 1]
                    if (teams != null && teams.isNotEmpty()) {
                        textView.text = teams[0].wins.toString()
                    }
                }

                // Partidos perdidos por cada uno de los equipos
                textViewListPerdidos.forEachIndexed { index, textView ->
                    val teams = teamsRank[index + 1]
                    if (teams != null && teams.isNotEmpty()) {
                        textView.text = teams[0].losses.toString()
                    }
                }

                // Escudos de los equipos dependiendo de la clasificación
                imageViewListEscudos.forEachIndexed { index, escudoEquipo ->
                    val teams = teamsRank[index + 1]
                    if (teams != null && teams.isNotEmpty()) {
                        val drawableResId = when (teams[0].team.name) {
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
                        escudoEquipo.setImageResource(drawableResId)
                    }
                }
            }
        }
    }
}