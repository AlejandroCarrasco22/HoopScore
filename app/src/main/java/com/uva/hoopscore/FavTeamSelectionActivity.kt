package com.uva.hoopscore

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FavTeamSelectionActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav_team_selection)

        firebaseAuth = FirebaseAuth.getInstance()

        // Se definen cada layout por equipo de la lista
        val barcelonaLayout: LinearLayout = findViewById(R.id.barcelona_layout)
        val baskoniaLayout: LinearLayout = findViewById(R.id.baskonia_layout)
        val unicajaLayout: LinearLayout = findViewById(R.id.unicaja_layout)
        val obradoiroLayout: LinearLayout = findViewById(R.id.obradoiro_layout)
        val palenciaLayout: LinearLayout = findViewById(R.id.palencia_layout)
        val madridLayout: LinearLayout = findViewById(R.id.madrid_layout)
        val andorraLayout: LinearLayout = findViewById(R.id.andorra_layout)
        val badalonaLayout: LinearLayout = findViewById(R.id.badalona_layout)
        val bilbaoLayout: LinearLayout = findViewById(R.id.bilbao_layout)
        val breoganLayout: LinearLayout = findViewById(R.id.breogan_layout)
        val gironaLayout: LinearLayout = findViewById(R.id.girona_layout)
        val granCanariaLayout: LinearLayout = findViewById(R.id.grancanaria_layout)
        val granadaLayout: LinearLayout = findViewById(R.id.granada_layout)
        val manresaLayout: LinearLayout = findViewById(R.id.manresa_layout)
        val murciaLayout: LinearLayout = findViewById(R.id.murcia_layout)
        val tenerifeLayout: LinearLayout = findViewById(R.id.tenerife_layout)
        val valenciaLayout: LinearLayout = findViewById(R.id.valencia_layout)
        val zaragozaLayout: LinearLayout = findViewById(R.id.zaragoza_layout)


        // Se manejan los eventos de todos los linear layout de los equipos disponibles en la lista

        barcelonaLayout.setOnClickListener{
            agregarInfoBD("Barça")
        }


        baskoniaLayout.setOnClickListener{
            agregarInfoBD("Baskonia")
        }


        unicajaLayout.setOnClickListener{
            agregarInfoBD("Unicaja")
        }


        obradoiroLayout.setOnClickListener{
            agregarInfoBD("Obradoiro")
        }


        palenciaLayout.setOnClickListener{
            agregarInfoBD("Zunder Palencia")
        }


        madridLayout.setOnClickListener{
            agregarInfoBD("Real Madrid")
        }


        andorraLayout.setOnClickListener{
            agregarInfoBD("Morabanc Andorra")
        }


        badalonaLayout.setOnClickListener{
            agregarInfoBD("Joventut Badalona")
        }


        bilbaoLayout.setOnClickListener{
            agregarInfoBD("Bilbao Basket")
        }


        breoganLayout.setOnClickListener{
            agregarInfoBD("Breogán")
        }


        gironaLayout.setOnClickListener{
            agregarInfoBD("Bàsquet Girona")
        }


        granCanariaLayout.setOnClickListener{
            agregarInfoBD("Gran Canaria")
        }


        granadaLayout.setOnClickListener{
            agregarInfoBD("Covirán Granada")
        }


        manresaLayout.setOnClickListener{
            agregarInfoBD("Manresa")
        }


        murciaLayout.setOnClickListener{
            agregarInfoBD("UCAM Murcia")
        }


        tenerifeLayout.setOnClickListener{
            agregarInfoBD("Lenovo Tenerife")
        }


        valenciaLayout.setOnClickListener{
            agregarInfoBD("Valencia Basket")
        }


        zaragozaLayout.setOnClickListener{
            agregarInfoBD("Casademont Zaragoza")
        }



    }

    // Función que añade al usuario el equipo que ha seleccionado y lo guarda en la base de datos
    private fun agregarInfoBD(equipo: String) {
        val uid = firebaseAuth.uid      //recogemos el id del usuario


        Log.i("TAG", uid.toString())
        //Se actualiza el campo equipo del usuario que se ha registrado
        val reference = FirebaseDatabase.getInstance().getReference("Usuarios")
        reference.child(uid.toString()).child("equipo").setValue(equipo)
            .addOnSuccessListener {
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "No se puedo guardar la información debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }



}