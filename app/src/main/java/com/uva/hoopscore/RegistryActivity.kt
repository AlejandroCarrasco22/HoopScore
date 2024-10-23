package com.uva.hoopscore

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.Date
import java.util.Locale

class RegistryActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    lateinit var campoUsuario : EditText
    lateinit var campoNombre : EditText
    lateinit var campoCorreo : EditText
    lateinit var campoFechaNacimiento : EditText
    lateinit var campoContrasena : EditText
    lateinit var campoRepetirContrasena : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        val textInicioSesion: TextView = findViewById(R.id.textInicioSesion)
        val botonRegistrarse: Button = findViewById(R.id.botonRegistro)
        val campoFecha: EditText = findViewById(R.id.campoNacimiento)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)


        // Cuando se pulse en el texto inferior se mostrará la pantalla de inicio de sesión
        textInicioSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Cuando se pule el boton de registrarse se creará al usuario
        botonRegistrarse.setOnClickListener {
            validarUsuario()
        }

        campoFecha.setOnClickListener{
            mostrarDatePick()
        }
    }

    private fun validarUsuario(){

        // Se definen las variables de todos los editText del formulario

        campoUsuario = findViewById(R.id.campoUsuario)
        campoNombre = findViewById(R.id.campoNombre)
        campoCorreo = findViewById(R.id.campoCorreo)
        campoFechaNacimiento = findViewById(R.id.campoNacimiento)
        campoContrasena = findViewById(R.id.campoContrasena)
        campoRepetirContrasena = findViewById(R.id.campoRepetirContrasena)


        // Se define una variable por cada editText del formulario para realizar las respectivas comprobaciones
        val usuario: String = campoUsuario.text.toString().trim()
        val nombre: String = campoNombre.text.toString().trim()
        val email: String = campoCorreo.text.toString().trim()
        val fechaNacimiento: String = campoFechaNacimiento.text.toString()
        val contrasena: String = campoContrasena.text.toString()
        val repetirContrasena: String = campoRepetirContrasena.text.toString()

        // Se definen las variables para comparar el campo fecha del formulario
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = sdf.format(Date()) // Fecha actual

        val date = sdf.parse(currentDate)
        val fechaIntroducida = sdf.parse(fechaNacimiento)

        // Comprobaciones para validar el formulario de registro
        if(usuario.isEmpty()){
            campoUsuario.setError("El nombre de usuario no puede estar vacío")
            campoUsuario.requestFocus()
        } else if(nombre.isEmpty()){
            campoNombre.setError("El nombre no puede estar vacío")
            campoNombre.requestFocus()
        } else if(email.isEmpty()){
            campoCorreo.setError("El campo Correo no puede estar vacío")
            campoCorreo.requestFocus()
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //Comprueba que el correo esté en un formato correcto
            campoCorreo.setError("El correo no es válido")
            campoCorreo.requestFocus()

        } else if(fechaNacimiento.isEmpty()){
            campoFechaNacimiento.setError("La fecha de nacimiento no puede estar vacía")
            campoFechaNacimiento.requestFocus()
        } else if(fechaIntroducida >= date){
            campoFechaNacimiento.setError("La fecha no puede ser mayor que la fecha actual")
            campoFechaNacimiento.requestFocus()
        } else if(contrasena.isEmpty()){
            campoContrasena.setError("La contraseña no puede estar vacía")
            campoContrasena.requestFocus()
        } else if(contrasena.length < 6){
            //La contraseña debe mínimo de 6 carácteres
            campoContrasena.setError("La contraseña debe tener una longitud mínima de 6 caracteres")
            campoContrasena.requestFocus()
        } else if (repetirContrasena.isEmpty()){
            campoRepetirContrasena.setError("La contraseña repetida no puede estar vacía")
            campoRepetirContrasena.requestFocus()
        } else if(contrasena != repetirContrasena){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
        } else {
            crearCuentaUsuario(email, contrasena)
        }
    }

    //Crea la cuenta del usuario con firebase con el email y contraseña
    private fun crearCuentaUsuario(email: String, contrasena: String) {
        progressDialog.setMessage("Creando cuenta")
        progressDialog.dismiss()
        firebaseAuth.createUserWithEmailAndPassword(email, contrasena)
            .addOnSuccessListener {
                agregarInfoBD()
            }
            .addOnFailureListener{ e ->
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Ha fallado la creación de la cuenta debido a ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }

    // Función para agregar toda la información relacionada con el usuario que se acaba de registrar
    private fun agregarInfoBD() {
        progressDialog.setMessage("Guardando información...")
        val tiempo = System.currentTimeMillis()     //Obtenemos el tiempo del sistema
        val uid = firebaseAuth.uid      //recogemos el id del usuario

        val datos_usuario : HashMap<String, Any?> = HashMap()
        datos_usuario["uid"] = uid
        datos_usuario["usuario"] = campoUsuario.text.toString().trim()
        datos_usuario["nombre"] = campoNombre.text.toString().trim()
        datos_usuario["email"] = campoCorreo.text.toString().trim()
        datos_usuario["fecha_nacimiento"] = campoFechaNacimiento.text.toString()
        datos_usuario["tiempo_registro"] = tiempo
        datos_usuario["equipo"] = ""


        //Establecemos el nombre de la base de datos donde se va a guargar
        val reference = FirebaseDatabase.getInstance().getReference("Usuarios")
        reference.child(uid!!)
            .setValue(datos_usuario)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Cuenta creada", Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, FavTeamSelectionActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "No se puedo guardar la información debido a ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    // Función para mostrar el calendario del campo correspondiente a la fecha de nacimiento
    private fun mostrarDatePick() {
        val campoFecha: EditText = findViewById(R.id.campoNacimiento)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Aquí obtienes la fecha seleccionada
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                // Puedes mostrar la fecha en el EditText o hacer lo que necesites con ella
                campoFecha.setText(selectedDate)
            },
            // Establece la fecha por defecto cuando se abre el DatePicker
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}