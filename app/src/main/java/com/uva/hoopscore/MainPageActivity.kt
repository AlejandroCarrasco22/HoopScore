package com.uva.hoopscore

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uva.hoopscore.Adapter.PartidosAdapter
import com.uva.hoopscore.Modelo.Result
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.Locale

class MainPageActivity : AppCompatActivity() {
    private val urlBase = "https://api.sportradar.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val iconoUser : ImageView = findViewById(R.id.user)
        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txtTitulo)
        val retroceder: ImageView = findViewById(R.id.menor_que)
        val avanzar: ImageView = findViewById(R.id.mayor_que)
        val textoFecha: TextView = findViewById(R.id.textFechaPartidos)
        var fechaActual = intent.getStringExtra("fecha")


        // Objeto Retrofit para consumir servicios
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Se comprueba si es la primera vez que se accede a la pantalla. Si es null el valor extra del intent se pone la fecha del sistema (HOY)
        if (fechaActual == null){
            val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            fechaActual = formato.format(Date())
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

        // Cuando se pulse en el nombre de la aplicación se mostrará la pantalla Main Page
        retroceder.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            val fechaModificada = restarDia((fechaActual.toString()))
            intent.putExtra("fecha", fechaModificada)
            startActivity(intent)
        }

        // Cuando se pulse en el nombre de la aplicación se mostrará la pantalla Main Page
        avanzar.setOnClickListener {
            val intent = Intent(this, MainPageActivity::class.java)
            val fechaModificada = sumarDia((fechaActual.toString()))

            val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val fechaHoyString = formato.format(Date())

            val fechaModificadaDate = formato.parse(fechaModificada)
            val fechaHoyDate = formato.parse(fechaHoyString)

            if (fechaModificadaDate > fechaHoyDate){
                Toast.makeText(applicationContext, "No se puede acceder a partidos posteriores a la fecha actual", Toast.LENGTH_LONG)
                    .show()
            } else {
                intent.putExtra("fecha", fechaModificada )
                startActivity(intent)
            }

        }

        // Se modifica el elemento fecha de la interfaz dependiendo del día en el que se encuentre ubicado
        textoFecha.text = fechaActual


        // Se realiza la llamada a la API para obtener la información necesaria
        val service = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            val response = service.getPartidos(fechaActual.toString())
            val partidosAcb = response.results
                .filter { it.sport_event.tournament.id == "sr:tournament:264" }
        initRecyclerView(partidosAcb)
        }
    }

    // Funcion para llamar al RecyclerView con los partidos del día.
    private fun initRecyclerView(listaPartidos: List<Result>){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPartidos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            PartidosAdapter(listaPartidos) {result ->
                onItemSelected(
                    result
                )
            }
    }

    // Cuando se pulse cada uno de los partidos se mostrará toda la información asociada
    fun onItemSelected(result: Result){
        val intent = Intent(this, GameStatsActivity::class.java)
        // Se añade la información asociada al partido
        intent.putExtra("idPartido", result.sport_event.id)
        startActivity(intent)
    }

    // Funcion para restar un dia al String de la fecha
    fun restarDia(fechaString: String): String {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fecha = formato.parse(fechaString)
        val calendar = Calendar.getInstance()
        calendar.time = fecha
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        println(formato.format(calendar.time))
        return formato.format(calendar.time)
    }

    // Funcion para sumar un dia al String de la fecha
    fun sumarDia(fechaString: String): String {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fecha = formato.parse(fechaString)
        val calendar = Calendar.getInstance()
        calendar.time = fecha
        calendar.add(Calendar.DAY_OF_YEAR, +1)
        println(formato.format(calendar.time))
        return formato.format(calendar.time)
    }
}