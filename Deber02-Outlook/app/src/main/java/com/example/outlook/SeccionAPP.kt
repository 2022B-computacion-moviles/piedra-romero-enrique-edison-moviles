package com.example.outlook

import android.app.Application
import androidx.room.Room

class SeccionAPP : Application(){
    val room= Room
        .databaseBuilder(this, SeccionDB::class.java, "seccion")
        .build()
}