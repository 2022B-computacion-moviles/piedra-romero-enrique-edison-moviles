package com.example.adle_exam_2b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.adle_exam_2b.data.dao.DAOFactory
import com.example.adle_exam_2b.data.util.ComponentData
import com.example.adle_exam_2b.data.util.DeviceData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createButton = findViewById<Button>(R.id.btn_create_initial_data)
        val startButton = findViewById<Button>(R.id.btn_start_app)

        createButton.setOnClickListener {
            createInitialData()
        }

        startButton.setOnClickListener {
            openActivity(DeviceList::class.java)
        }
    }

    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }

    private fun createInitialData() {
        for (device in DeviceData.deviceEntityData) {
            DAOFactory.factory.getDeviceDAO().create(device)
        }

        for (component in ComponentData.componentEntityData) {
            DAOFactory.factory.getComponentDAO().create(component)
        }
    }
}