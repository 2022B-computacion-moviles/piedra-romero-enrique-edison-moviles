package com.example.outlook

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities=[Seccion::class],
    version=1
)

abstract class SeccionDB: RoomDatabase(){
    abstract fun seccionDao(): SeccionDAO
}