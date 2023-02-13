package com.example.outlook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        irActividad(GRecyclerView::class.java)


    }

    fun irActividad(clase:Class<*>){
        val intent= Intent(this, clase)
        startActivity(intent)
    }
}