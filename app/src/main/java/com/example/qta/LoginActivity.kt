package com.example.qta

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.qta.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asegurate que tu XML se llame así

        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)

        val imageViewBack = findViewById<ImageView>(R.id.backBtn)
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Comportamiento "atrás" moderno
        }

        btnIngresar.setOnClickListener {
            val correoIngresado = etCorreo.text.toString().trim()
            val contrasenaIngresada = etContrasena.text.toString()

            val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("correoUsuario", correoIngresado)
            editor.apply()

            if (correoIngresado.isNotEmpty() && contrasenaIngresada.isNotEmpty()) {
                val intent = Intent(this, DosMitadesActivity::class.java)
                intent.putExtra("correo", correoIngresado)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}