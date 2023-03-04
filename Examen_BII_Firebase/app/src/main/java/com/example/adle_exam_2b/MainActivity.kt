package com.example.adle_exam_2b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.util.DataCarro
import com.example.adle_exam_2b.data.util.DataConcesionario

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openActivity(ListConcesionario::class.java)

    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }


}