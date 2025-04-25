package com.example.qta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputEditText
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmentos)

        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val correo = prefs.getString("correoUsuario", null)

        val tvSaludo = findViewById<TextView>(R.id.textViewGreeting)
        tvSaludo.text = if (correo != null) "Hola $correo" else "Hola invitado"

        val imageViewClose = findViewById<ImageView>(R.id.exitBtn)
        val textInputLayoutInputString = findViewById<TextInputLayout>(R.id.textInputLayoutInputString)
        val editTextInputString = textInputLayoutInputString.findViewById<TextInputEditText>(R.id.editTextInputString)
        val buttonSolve = findViewById<MaterialButton>(R.id.buttonSolve)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val textViewErrorMessage = findViewById<TextView>(R.id.textViewErrorMessage)

        imageViewClose.setOnClickListener {
            finish()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.parte3

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.parte1 -> {
                    startActivity(Intent(this, DosMitadesActivity::class.java))
                    finish()
                    true
                }
                R.id.parte2 -> {
                    startActivity(Intent(this, DosPalabrasActivity::class.java))
                    finish()
                    true
                }
                R.id.parte3 -> true
                else -> false
            }
        }

        buttonSolve.setOnClickListener {
            val inputString = editTextInputString?.text?.toString() ?: ""

            textViewResult.visibility = View.GONE
            textViewErrorMessage.visibility = View.GONE
            textInputLayoutInputString.error = null

            if (inputString.count { it.lowercaseChar() == 'h' } < 2) {
                textViewErrorMessage.visibility = View.VISIBLE
            } else {
                val firstHIndex = inputString.indexOf('h', ignoreCase = true)
                val lastHIndex = inputString.lastIndexOf('h', ignoreCase = true)

                if (firstHIndex != -1 && lastHIndex != -1 && firstHIndex < lastHIndex) {
                    val resultString = inputString.substring(0, firstHIndex) +
                            inputString.substring(lastHIndex + 1)

                    textViewResult.text = resultString
                    textViewResult.visibility = View.VISIBLE
                } else {
                    textViewErrorMessage.text = "Error interno al procesar la cadena."
                    textViewErrorMessage.visibility = View.VISIBLE
                    Toast.makeText(this, "No se pudo encontrar el fragmento según las reglas.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}