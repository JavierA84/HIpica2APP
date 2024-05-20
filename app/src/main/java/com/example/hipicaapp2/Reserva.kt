package com.example.hipicaapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reservas")
data class Reserva(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreJinete: String,
    val movil: String,
    val nombreCaballo: String,
    val fecha: Date,
    val hora: String,
    val comentario: String
)
