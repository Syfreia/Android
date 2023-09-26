package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        val bdINFO = findViewById<TextView>(R.id.textView_bd)
        val btn = findViewById<Button>(R.id.mostrar_BTN)

        btn.setOnClickListener {
            val documentPath = "/personas/persona1"

            db.document(documentPath).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val name = documentSnapshot.getString("nombre")
                        val city = documentSnapshot.getString("ciudad")
                        bdINFO.text = "$name -> $city"
                    } else {
                        bdINFO.text = getString(R.string.Not_found)

                    }
                }
                .addOnFailureListener { e ->
                    // Handle errors
                    bdINFO.text = "Error: ${e.message}"
                }
        }
    }
}