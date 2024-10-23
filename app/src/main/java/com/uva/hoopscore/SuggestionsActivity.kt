package com.uva.hoopscore

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SuggestionsActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var progressDialog : ProgressDialog
    lateinit var botonesOpciones : RadioGroup
    lateinit var campoTexto : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestions)

        val iconoUser : ImageView = findViewById(R.id.user)
        val iconoJugador: ImageView = findViewById(R.id.jugador_bottom)
        val iconoCasa: ImageView = findViewById(R.id.casa_bottom)
        val iconoMegafono: ImageView = findViewById(R.id.megafono_bottom)
        val textoHoopScore: TextView = findViewById(R.id.txtTitulo)
        val botonEnviarSugerencia: Button = findViewById(R.id.botonEnviarSugerencia)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

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
        botonEnviarSugerencia.setOnClickListener {
            enviarSugerencia()
        }

    }

    // Función que registra la sugerencia que ha propuesto el usuario en la base de datos
    fun enviarSugerencia(){
        // Declaro las variables que están asociadas a los componentes
        campoTexto = findViewById(R.id.textoSugerencia)
        botonesOpciones = findViewById(R.id.radioGroup)
        var tipoSugerencia: String = ""

        val checkedRadioButtonId = botonesOpciones.checkedRadioButtonId

        if (checkedRadioButtonId != -1) { // Si hay algún RadioButton seleccionado
            val radioButton: RadioButton = findViewById(checkedRadioButtonId)
            tipoSugerencia = radioButton.text.toString()
        }
        Log.i("TAG", tipoSugerencia)
            // Guardo el contenido que contiene las variables
        val textoSugerencia: String = campoTexto.getText().toString()

        // Comprobación de que el formulario enviado es correcto.
        // Si no hay ningún problema se guarda la sugerencia en la BD
        if (textoSugerencia.isEmpty()){
            campoTexto.setError("El campo de la sugerencia no puede estar vacío")
            campoTexto.requestFocus()
        } else if (botonesOpciones.checkedRadioButtonId == -1){
            Toast.makeText(this, "Debes seleccionar al menos una de las opciones", Toast.LENGTH_LONG).show()
        } else{
            agregarIngoBD(textoSugerencia, tipoSugerencia)
        }

    }


    private fun agregarIngoBD(textoSugerencia: String, tipoSugerencia: String) {
        progressDialog.setMessage("Guardando información...")
        val tiempo = System.currentTimeMillis()     //Obtenemos el tiempo del sistema
        val uid = firebaseAuth.uid      //recogemos el id del usuario

        Log.i("TAG", "${firebaseAuth.uid}")
        val datos_sugerencia : HashMap<String, Any?> = HashMap()
        datos_sugerencia["id"] = tiempo
        datos_sugerencia["tipo_sugerencia"] = tipoSugerencia
        datos_sugerencia["sugerencia"] = textoSugerencia
        datos_sugerencia["uid"] = "${firebaseAuth.uid}"

        val ref = FirebaseDatabase.getInstance().getReference("Sugerencias")
        ref.child("$tiempo")
            .setValue(datos_sugerencia)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Se envió la sugerencia", Toast.LENGTH_SHORT).show()
                campoTexto.setText("")

            }
            .addOnFailureListener {e ->
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "No se agregó la sugerencia debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}