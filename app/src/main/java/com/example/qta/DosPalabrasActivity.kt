package com.example.qta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DosPalabrasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos_palabras)

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val correo = prefs.getString("correoUsuario", null)

        val tvSaludo = findViewById<TextView>(R.id.tvSaludo) // Asegúrate de tener este TextView en tu XML
        tvSaludo.text = if (correo != null) "Hola $correo" else "Hola invitado"

        val editText = findViewById<EditText>(R.id.etRespuesta)
        val buttonSolucionar = findViewById<Button>(R.id.btnVerificar)
        val resultadoTextView = findViewById<TextView>(R.id.tvResultado)

        buttonSolucionar.setOnClickListener {
            val entrada = editText.text.toString().trim()
            val palabras = entrada.split(" ")

            if (palabras.size == 2) {
                val resultado = "${palabras[1]} ${palabras[0]}"
                resultadoTextView.text = resultado
            } else {
                resultadoTextView.text = "Por favor, ingresa solo 2 palabras separadas por un espacio."
            }
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.parte2 // Estoy en esta Pantalla

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.parte1 -> {
                    startActivity(Intent(this, DosMitadesActivity::class.java))
                    finish()
                    true
                }
                R.id.parte2 -> true
                R.id.parte3 -> {
                    startActivity(Intent(this, FragmentosActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}