package com.uva.hoopscore.Adapter


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uva.hoopscore.Modelo.Result
import com.uva.hoopscore.R

class PartidosViewHolder(view: View): RecyclerView.ViewHolder(view) {


    val equipo1 = view.findViewById<TextView>(R.id.textView1)
    val equipo2 = view.findViewById<TextView>(R.id.textView3)
    val escudoEquipo1 = view.findViewById<ImageView>(R.id.imageView1)
    val escudoEquipo2 = view.findViewById<ImageView>(R.id.imageView2)
    val resultadoEquipoLocal = view.findViewById<TextView>(R.id.textView2)
    val resultadoEquipoVisitante = view.findViewById<TextView>(R.id.textView4)

    // Muestra por pantalla toda la información de cada uno de los item Partido
    fun render(partido: Result, onClickListener: (Result) -> Unit) {
        val primerCompetidor = partido.sport_event.competitors[0]
        val segundoCompetidor = partido.sport_event.competitors[1]

        // Nombre del equipo Local
        val primerCompetidorName = when (primerCompetidor.name) {
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
        equipo1.text = primerCompetidorName

        // Nombre del equipo visitante
        val segundoCompetidorName = when (segundoCompetidor.name) {
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
        equipo2.text = segundoCompetidorName

        // Escudo del equipo local
        val drawableResId = when (primerCompetidor.name) {
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
        escudoEquipo1.setImageResource(drawableResId)

        // Escudo del equipo visitante
        val drawableResId2 = when (segundoCompetidor.name) {
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
        escudoEquipo2.setImageResource(drawableResId2)

        // Resultado equipo local
        val resultadoEquipoLocalApi = partido.sport_event_status.home_score
        resultadoEquipoLocal.text = resultadoEquipoLocalApi.toString()

        // Resultado equipo visitante
        val resultadoEquipoVisitanteApi = partido.sport_event_status.away_score
        resultadoEquipoVisitante.text = resultadoEquipoVisitanteApi.toString()

        // Función que se ejecuta cuando se hace click sobre cada uno de los partidos
        itemView.setOnClickListener{ onClickListener(partido)}
    }
}