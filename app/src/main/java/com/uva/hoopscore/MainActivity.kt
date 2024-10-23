package com.uva.hoopscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {

    // Tiempo (ms) que estará la pantalla antes de que cambie a la siguiente
    private val tiempoRetraso: Long = 2000

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Se obtiene la instacia de la base de datos asociada a la aplicación
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        // Se comprueba si el usuario ya estaba logeado.
        // En caso de que estuviera la pantalla principal cambiar a la MainPage
        // En caso de que no estuviera logeado la pantalla principal cambia a la pantalla de Login
        if (currentUser == null){
            // Crear un Handler para manejar el retraso
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, tiempoRetraso)
        } else {
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
                finish()
            }, tiempoRetraso)
        }
    }
}
