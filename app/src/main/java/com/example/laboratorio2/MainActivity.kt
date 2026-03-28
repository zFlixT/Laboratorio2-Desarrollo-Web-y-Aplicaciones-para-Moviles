package com.example.laboratorio2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Persona(
    val nombre: String,
    val edad: String,
    val departamento: String
)

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etDepartamento: EditText
    private lateinit var btnAgregar: Button
    private lateinit var listViewNombres: ListView
    private lateinit var tvDetalle: TextView

    private val listaPersonas = ArrayList<Persona>()
    private val listaNombres = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etDepartamento = findViewById(R.id.etDepartamento)
        btnAgregar = findViewById(R.id.btnAgregar)
        listViewNombres = findViewById(R.id.listViewNombres)
        tvDetalle = findViewById(R.id.tvDetalle)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNombres)
        listViewNombres.adapter = adapter

        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val edad = etEdad.text.toString().trim()
            val departamento = etDepartamento.text.toString().trim()

            if (nombre.isEmpty() || edad.isEmpty() || departamento.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val persona = Persona(nombre, edad, departamento)
                listaPersonas.add(persona)
                listaNombres.add(nombre)
                adapter.notifyDataSetChanged()

                etNombre.text.clear()
                etEdad.text.clear()
                etDepartamento.text.clear()

                Toast.makeText(this, "Datos agregados", Toast.LENGTH_SHORT).show()
            }
        }

        listViewNombres.setOnItemClickListener { _, _, position, _ ->
            val personaSeleccionada = listaPersonas[position]
            tvDetalle.text = "Edad: ${personaSeleccionada.edad}\nDepartamento: ${personaSeleccionada.departamento}"
        }
    }
}