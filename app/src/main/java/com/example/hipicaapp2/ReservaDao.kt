package com.example.hipicaapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hipicaapp.data.Reserva

@Dao
interface ReservaDao {

    @Query("SELECT * FROM reserva_table ORDER BY fecha DESC, hora DESC")
    fun getAllReservas(): LiveData<List<Reserva>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reserva: Reserva)

    @Query("SELECT * FROM reserva_table WHERE fecha LIKE :fecha AND hora LIKE :hora ORDER BY fecha DESC, hora DESC")
    fun searchReservas(fecha: String, hora: String): LiveData<List<Reserva>>
}
