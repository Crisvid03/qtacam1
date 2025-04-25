package com.example.qta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class DosPalabrasActivity : AppCompatActivity() {

    private lateinit var correo: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos_palabras)

        // Obtener el correo desde el intent
        correo = intent.getStringExtra("correo") ?: "usuario"

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
                    startActivity(Intent(this, DosMitadesActivity::class.java).apply {
                        putExtra("correo", correo)
                    })
                    finish()
                    true
                }
                R.id.parte2 -> true
                R.id.parte3 -> {
                    startActivity(Intent(this, FragmentosActivity::class.java).apply {
                        putExtra("correo", correo)
                    })
                    finish()
                    true
                }
                else -> false
            }
        }
    }

}
