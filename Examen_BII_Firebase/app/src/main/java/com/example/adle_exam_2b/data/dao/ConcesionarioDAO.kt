package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.entity.Concesionario

interface ConcesionarioDAO: GenericDAO<Concesionario, Int> {

    fun getAllConcesionarios(onSuccess: (ArrayList<Concesionario>) -> Unit)

}