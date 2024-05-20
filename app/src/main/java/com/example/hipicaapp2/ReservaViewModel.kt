package com.example.hipicaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hipicaapp.data.Reserva
import com.example.hipicaapp.data.ReservaRepository
import kotlinx.coroutines.launch

class ReservaViewModel(private val repository: ReservaRepository) : ViewModel() {

    val allReservas: LiveData<List<Reserva>> = repository.allReservas

    fun insert(reserva: Reserva) = viewModelScope.launch {
        repository.insert(reserva)
    }

    fun searchReservas(fecha: String, hora: String): LiveData<List<Reserva>> {
        return repository.searchReservas(fecha, hora)
    }
}
