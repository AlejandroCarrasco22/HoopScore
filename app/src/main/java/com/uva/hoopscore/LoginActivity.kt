package com.uva.hoopscore


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.uva.hoopscore.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    private lateinit var campoCorreo : EditText
    private lateinit var campoContraseña : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textRegistrarse: TextView = findViewById(R.id.textoRegistrarse)
        val botonIniciarSesion: Button = findViewById(R.id.botonIniciarSesion)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Iniciando sesión....")
        progressDialog.setCanceledOnTouchOutside(false)


        // Cuando se pulse en el texto inferior se mostrará la pantalla de registro
        textRegistrarse.setOnClickListener {
            val intent = Intent(this, RegistryActivity::class.java)
            startActivity(intent)
        }

        //Cuando se pulse el botón de "Iniciar Sesión" se mostrará la pantalla principal
        botonIniciarSesion.setOnClickListener{
            validarInformacion()
        }

    }

    // Función que valida los datos de entrada
    private fun validarInformacion() {
        campoCorreo = findViewById(R.id.campoUsuario)
        campoContraseña = findViewById(R.id.campoContrasena)

        val email: String = campoCorreo.text.toString().trim()
        val password: String = campoContraseña.text.toString().trim()

        if(email.isEmpty()){
            campoCorreo.setError("El campo del correo no puede estar vacío")
            campoCorreo.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            campoCorreo.setError("El correo es invalido")
            campoCorreo.requestFocus()
        } else if(password.isEmpty()){
            campoContraseña.setError("Ingrese una contraseña")
            campoContraseña.requestFocus()
        } else{
            login(email, password)
        }
    }

    // Se comprueba si el usuario introducido existe en la base de datos,
    // debido a que se había creado una cuenta anteriormente
    private fun login(email: String, password: String) {
        progressDialog.setMessage("Iniciando sesión")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            .addOnFailureListener {e ->
                Toast.makeText(applicationContext, "No se pudo iniciar la sesion debido a ${e.message}",
                    Toast.LENGTH_SHORT).show()

            }

    }
}