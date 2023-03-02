package com.example.adle_exam_2b.data.dao

import com.example.adle_exam_2b.data.entity.Carro

interface CarroDAO: GenericDAO<Carro, Int> {

    fun getAllCarrosByCodeCar(concesionarioCode: Int,onSuccess: (ArrayList<Carro>) -> Unit)

}