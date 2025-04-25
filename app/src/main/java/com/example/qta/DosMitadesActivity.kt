package com.example.qta

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class DosMitadesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos_mitades)

        // Obtener views
        val imageViewClose = findViewById<ImageView>(R.id.exitBtn)
        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val etRespuesta = findViewById<EditText>(R.id.etRespuesta)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        imageViewClose.setOnClickListener {
            finish()
        }

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val correo = prefs.getString("correoUsuario", null)

        tvSaludo.text = if (correo != null) "Hola $correo" else "Hola invitado"

        btnVerificar.setOnClickListener {
            val textoIngresado = etRespuesta.text.toString().trim()

            if (textoIngresado.isEmpty() || textoIngresado.length < 2) {
                tvResultado.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                tvResultado.text = "Ups! algo salió mal revisa tu cadena."
            } else {
                val resultado = intercambiarMitades(textoIngresado)
                tvResultado.setTextColor(resources.getColor(android.R.color.black))
                tvResultado.text = resultado
            }
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.parte1 // Estoy en esta Pantalla

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.parte1 -> {
                    // Ya estás aquí
                    true
                }
                R.id.parte2 -> {
                    startActivity(Intent(this, DosPalabrasActivity::class.java))
                    finish()
                    true
                }
                R.id.parte3 -> {
                    startActivity(Intent(this, FragmentosActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
    private fun intercambiarMitades(cadena: String): String {
        val longitud = cadena.length
        val mitad = longitud / 2

        return if (longitud % 2 == 0) {
            val primeraMitad = cadena.substring(0, mitad)
            val segundaMitad = cadena.substring(mitad)
            segundaMitad + primeraMitad
        } else {
            val primeraMitad = cadena.substring(0, mitad + 1)
            val segundaMitad = cadena.substring(mitad + 1)
            segundaMitad + primeraMitad
        }
    }
}