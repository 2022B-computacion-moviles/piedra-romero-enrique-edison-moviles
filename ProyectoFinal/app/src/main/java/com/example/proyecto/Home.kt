package com.example.proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.example.proyecto.data.firebase.FirebaseGlobal
import com.example.proyecto.data.firebase.FirebaseInstruments
import com.example.proyecto.data.util.DataInstruments
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnCerrar = findViewById<Button>(R.id.btn_home_close)
        val emailText = findViewById<TextView>(R.id.home_email)
        var emailUser= intent.getStringExtra("emailUser")

        emailText.text=emailUser

        btnCerrar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }


        for (instrument in DataInstruments.instrumentsData) {
            FirebaseGlobal.firebaseInstruments.create(instrument)
        }

        val text_aux=findViewById<TextView>(R.id.view_instrument)
        
        FirebaseGlobal.firebaseInstruments.getAllInstruments { instruments ->
            var aux=""
            for (instrument in instruments) {
                aux+=instrument.nombre +" ";
            }

            text_aux.text=aux
        }



    }


}