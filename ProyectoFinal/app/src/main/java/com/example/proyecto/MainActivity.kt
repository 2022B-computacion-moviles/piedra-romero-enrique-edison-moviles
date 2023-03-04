package com.example.proyecto

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()



    }

    private fun setup(){
        val registrar = findViewById<Button>(R.id.btn_login_registrar)
        val acceder = findViewById<Button>(R.id.btn_login_acceder)
        val emailEditText = findViewById<EditText>(R.id.login_email)
        val passwordEditText = findViewById<EditText>(R.id.login_password)

        registrar.setOnClickListener {
            if(emailEditText.text.isNotEmpty() &&  passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this, Home::class.java).apply {
                                putExtra("emailUser",it.result?.user?.email?:"")
                            })
                        }
                        else{
                            showAlert()
                        }
                    }
            }
        }

        acceder.setOnClickListener {
            if(emailEditText.text.isNotEmpty() &&  passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(this, Home::class.java).apply {
                                putExtra("emailUser",it.result?.user?.email?:"")
                            })
                        }
                        else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        AlertDialog.Builder(this)
            .setTitle("ERROR")
            .setMessage("No se ha podido authentificar este usuario")
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }



}