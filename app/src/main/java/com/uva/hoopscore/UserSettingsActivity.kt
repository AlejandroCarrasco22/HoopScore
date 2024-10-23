package com.uva.hoopscore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uva.hoopscore.Modelo.Usuario

class UserSettingsActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)


        firebaseAuth = FirebaseAuth.getInstance()

        val btnCerrarSesion: Button = findViewById(R.id.btnCerrarSesion)
        val btnPremium : Button = findViewById(R.id.boton_premium)

        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txt_titulo)


        val tituloUsuario: TextView = findViewById(R.id.tituloNombreUsuario)
        val campoNombre: TextView = findViewById(R.id.contenido_nombre)
        val campoEmail: TextView = findViewById(R.id.contenido_email)
        val campoFecha: TextView = findViewById(R.id.contenido_fecha)
        val campoEquipo: TextView = findViewById(R.id.contenido_equipo)
        val escudoEquipo: ImageView = findViewById(R.id.escudo_equipo)


        // Cuando se pulse el botón de cerrar sesión se muestra la pantalla de Login
        // Además se borra la sesión del usuario de Firebase

        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this@UserSettingsActivity, LoginActivity::class.java)
            startActivity(intent)
            Firebase.auth.signOut();
            finish() // Cierra la actividad actual
        }

        // Cuando se pulse el botón de hacerte premium saldrá un Toast informando de que la opción no está implementada
        btnPremium.setOnClickListener{
            Toast.makeText(this, "Estamos trabajando en ello...", Toast.LENGTH_LONG).show()
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


        val uid = firebaseAuth.currentUser?.uid // Obtener el UID del usuario actual


        // Se obtiene la información del usuario logeado y se muestra en la interfaz
        // su información correspondiente.
        val reference = FirebaseDatabase.getInstance().getReference("Usuarios")
        uid?.let { uid ->
            reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val usuario = snapshot.getValue(Usuario::class.java)
                        usuario?.let { user ->
                            tituloUsuario.text = usuario.usuario
                            campoNombre.text = usuario.nombre
                            campoEmail.text = usuario.email
                            campoFecha.text = usuario.fecha_nacimiento
                            campoEquipo.text = usuario.equipo
                            val equipo: String = usuario.equipo
                            val drawableResId = when (equipo) {
                                "Morabanc Andorra" -> R.drawable.andorra
                                "Barça" -> R.drawable.barcelona
                                "Joventut Badalona" -> R.drawable.badalona
                                "Baskonia" -> R.drawable.baskonia
                                "Bilbao Basket" -> R.drawable.bilbao
                                "Breogan" -> R.drawable.breogan
                                "Bàsquet Girona" -> R.drawable.girona
                                "Gran Canaria" -> R.drawable.gran_canaria
                                "Covirán Granada" -> R.drawable.granada
                                "Real Madrid" -> R.drawable.madrid
                                "Manresa" -> R.drawable.manresa
                                "UCAM Murcia" -> R.drawable.murcia
                                "Obradoiro" -> R.drawable.obradoiro
                                "Zunder Palencia" -> R.drawable.palencia
                                "Lenovo Tenerife" -> R.drawable.tenerife
                                "Unicaja" -> R.drawable.unicaja
                                "Valencia Basket" -> R.drawable.valencia
                                "Casademont Zaragoza" -> R.drawable.zaragoza
                                else -> R.drawable.palencia
                            }
                            escudoEquipo.setImageResource(drawableResId)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}