package com.example.hipicaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hipicaapp.databinding.FragmentReservaListBinding
import com.example.hipicaapp.viewmodel.ReservaViewModel

class ReservaListFragment : Fragment() {

    private val reservaViewModel: ReservaViewModel by viewModels()
    private var _binding: FragmentReservaListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ReservaListAdapter { reserva ->
            val action = ReservaListFragmentDirections.actionReservaListFragmentToAddEditReservaFragment(reserva.reservaId)
            findNavController().navigate(action)
        }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        reservaViewModel.allReservas.observe(viewLifecycleOwner) { reservas ->
            reservas?.let { adapter.setReservas(it) }
        }

        binding.buttonAddReserva.setOnClickListener {
            findNavController().navigate(R.id.action_reservaListFragment_to_addEditReservaFragment)
        }

        binding.buttonSearchReserva.setOnClickListener {
            val fecha = binding.editTextSearchFecha.text.toString()
            val hora = binding.editTextSearchHora.text.toString()
            reservaViewModel.searchReservas(fecha, hora).observe(viewLifecycleOwner) { reservas ->
                reservas?.let { adapter.setReservas(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
