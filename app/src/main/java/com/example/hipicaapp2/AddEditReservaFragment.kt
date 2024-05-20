package com.example.hipicaapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hipicaapp.R
import com.example.hipicaapp.data.Reserva
import com.example.hipicaapp.viewmodel.ReservaViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditReservaFragment : Fragment() {

    private val reservaViewModel: ReservaViewModel by viewModels()
    private val args: AddEditReservaFragmentArgs by navArgs()

    private lateinit var editTextNombreJinete: EditText
    private lateinit var editTextMovil: EditText
    private lateinit var editTextNombreCaballo: EditText
    private lateinit var buttonSelectFecha: Button
    private lateinit var buttonSelectHora: Button
    private lateinit var editTextComentario: EditText
    private lateinit var buttonSaveReserva: Button

    private var selectedFecha: Date? = null
    private var selectedHora: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_reserva, container, false)

        editTextNombreJinete = view.findViewById(R.id.edit_text_nombre_jinete)
        editTextMovil = view.findViewById(R.id.edit_text_movil)
        editTextNombreCaballo = view.findViewById(R.id.edit_text_nombre_caballo)
        buttonSelectFecha = view.findViewById(R.id.button_select_fecha)
        buttonSelectHora = view.findViewById(R.id.button_select_hora)
        editTextComentario = view.findViewById(R.id.edit_text_comentario)
        buttonSaveReserva = view.findViewById(R.id.button_save_reserva)

        buttonSelectFecha.setOnClickListener { showDatePickerDialog() }
        buttonSelectHora.setOnClickListener { showTimePickerDialog() }

        buttonSaveReserva.setOnClickListener { saveReserva() }

        return view
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedFecha = calendar.time
                buttonSelectFecha.text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedFecha)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                selectedHora = String.format("%02d:%02d", hourOfDay, minute)
                buttonSelectHora.text = selectedHora
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun saveReserva() {
        val nombreJinete = editTextNombreJinete.text.toString()
        val movil = editTextMovil.text.toString()
        val nombreCaballo = editTextNombreCaballo.text.toString()
        val comentario = editTextComentario.text.toString()

        if (TextUtils.isEmpty(nombreJinete) || TextUtils.isEmpty(movil) ||
            TextUtils.isEmpty(nombreCaballo) || selectedFecha == null || selectedHora == null) {
            // Show an error message to the user
            return
        }

        val reserva = Reserva(
            nombreJinete = nombreJinete,
            movil = movil,
            nombreCaballo = nombreCaballo,
            fecha = selectedFecha!!,
            hora = selectedHora!!,
            comentario = comentario
        )

        reservaViewModel.insert(reserva)
        findNavController().navigateUp()
    }
}
