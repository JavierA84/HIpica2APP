package com.example.hipicaapp

import androidx.lifecycle.LiveData
import com.example.hipicaapp.data.Reserva
import com.example.hipicaapp.data.ReservaDao

class ReservaRepository(private val reservaDao: ReservaDao) {

    val allReservas: LiveData<List<Reserva>> = reservaDao.getAllReservas()

    suspend fun insert(reserva: Reserva) {
        reservaDao.insert(reserva)
    }

    fun searchReservas(fecha: String, hora: String): LiveData<List<Reserva>> {
        return reservaDao.searchReservas(fecha, hora)
    }
}
