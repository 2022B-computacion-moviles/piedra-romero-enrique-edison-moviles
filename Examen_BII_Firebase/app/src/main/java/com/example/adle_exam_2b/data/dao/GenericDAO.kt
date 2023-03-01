package com.example.adle_exam_2b.data.dao

import androidx.recyclerview.widget.RecyclerView

interface GenericDAO<T, PK> {

    fun create(entity: T)
    fun read(code: PK, onSuccess: (T) -> Unit)
    fun update(entity: T)
    fun delete(code: PK, onSuccess: (Unit) -> Unit)
    fun getNextCode(onSuccess: (PK) -> Unit)

}